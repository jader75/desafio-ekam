package br.com.jader.desafio.ekam.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jader.desafio.ekam.domain.Beneficiario;
import br.com.jader.desafio.ekam.domain.Documento;
import br.com.jader.desafio.ekam.exception.BeneficiarioNaoEncontradoException;
import br.com.jader.desafio.ekam.repository.BeneficiarioRepository;
import br.com.jader.desafio.ekam.repository.DocumentoRepository;

@Service
public class BeneficiarioService {

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;

	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Transactional
	public Beneficiario cadastrarBeneficiario(Beneficiario beneficiario) {
		List<Documento> listaDocumentos = beneficiario.getDocumentos();

		Beneficiario beneficiarioSalvo = salvarBeneficiario(beneficiario);
		salvarDocumentos( listaDocumentos, beneficiarioSalvo );
		
		return beneficiarioSalvo; 
	}

	private Beneficiario salvarBeneficiario(Beneficiario beneficiario) {
		beneficiario.setDocumentos(null);
		Beneficiario beneficiarioSalvo = beneficiarioRepository.save( beneficiario );
		return beneficiarioSalvo;
	}

	private void salvarDocumentos(List<Documento> listaDocumentos, Beneficiario beneficiarioSalvo) {
		if(listaDocumentos != null && !listaDocumentos.isEmpty()) {
			List<Documento> documentosSalvos = listaDocumentos.stream()
					.peek(doc -> doc.setBeneficiario(beneficiarioSalvo)) // Associa o beneficiário a cada documento
					.map(documentoRepository::save) // Salva cada documento no repositório
					.collect(Collectors.toList()); // Coleta os documentos salvos em uma lista
			beneficiarioSalvo.setDocumentos( documentosSalvos );
		}
	}

	@Transactional(readOnly = true)
	public List<Beneficiario> listarBeneficiarios() {
		return beneficiarioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Beneficiario buscarBeneficiarioPorId(Long id) {
		return beneficiarioRepository.findById(id)
				.orElseThrow(() -> new BeneficiarioNaoEncontradoException("Beneficiario não encontrado com o ID: " + id));
	}

	@Transactional
	public Beneficiario atualizarBeneficiario(Beneficiario beneficiario) {
		verificarBeneficiario(beneficiario);
		
	    // Busca o beneficiário existente do banco de dados
	    Beneficiario beneficiarioExistente = beneficiarioRepository.findById(beneficiario.getId()).orElseThrow(() -> new BeneficiarioNaoEncontradoException("Beneficiário não encontrado"));
	    
	    // Atualiza os campos do beneficiário existente com os novos dados
	    atualizarDadosBeneficiario(beneficiario, beneficiarioExistente);

	    // Atualiza os documentos
	    atualizarDocumentos(beneficiario.getDocumentos(), beneficiarioExistente);
	    
	    return beneficiarioRepository.save(beneficiarioExistente);
	}

	/**
	 * Verifique se o beneficiário existe antes de atualizar
	 * @param beneficiario
	 * @throws BeneficiarioNaoEncontradoException
	 */
	private void verificarBeneficiario(Beneficiario beneficiario) throws BeneficiarioNaoEncontradoException {
		if (beneficiario.getId() == null || !beneficiarioRepository.existsById(beneficiario.getId())) {
			throw new BeneficiarioNaoEncontradoException("Beneficiário não encontrado");
		}
	}

	/**
	 * Atualiza os campos do beneficiário existente com os novos dados.
	 * </br>Campos Nome, Telefone e Data de Nascimento.
	 * @param beneficiario
	 * @param beneficiarioExistente
	 */
	private void atualizarDadosBeneficiario(Beneficiario beneficiario, Beneficiario beneficiarioExistente) {
		beneficiarioExistente.setNome(beneficiario.getNome());
	    beneficiarioExistente.setTelefone(beneficiario.getTelefone());
	    beneficiarioExistente.setDataNascimento(beneficiario.getDataNascimento());
	}

	@Transactional
	public void removerBeneficiario(Long id) {
        if (!beneficiarioRepository.existsById(id)) {
            throw new BeneficiarioNaoEncontradoException("Beneficiário não encontrado com o ID: " + id);
        }
		beneficiarioRepository.deleteById(id);
	}
	
	private void atualizarDocumentos(List<Documento> novosDocumentos, Beneficiario beneficiarioExistente) {
	    List<Documento> documentosExistentes = beneficiarioExistente.getDocumentos();

	    Set<String> tiposDocumentosNovos = criarSetDocumentosNovos(novosDocumentos);

	    excluirDocumentosRemovidos(documentosExistentes, tiposDocumentosNovos);

	    atualizarDocumentosLista(novosDocumentos, beneficiarioExistente, documentosExistentes);
	}

	/**
	 * Adiciona ou atualiza documentos na lista existente
	 * @param novosDocumentos
	 * @param beneficiarioExistente
	 * @param documentosExistentes
	 */
	private void atualizarDocumentosLista(List<Documento> novosDocumentos, Beneficiario beneficiarioExistente, List<Documento> documentosExistentes) {
	    novosDocumentos.forEach(novoDocumento -> {
	        Documento documentoExistente = filtrarDocumentosExistentes(documentosExistentes, novoDocumento);

	        if (documentoExistente != null) {
	            // Atualiza o documento existente
	            documentoExistente.setDescricao(novoDocumento.getDescricao());
	        } else {
	            criarDocumentoNovo(beneficiarioExistente, documentosExistentes, novoDocumento);
	        }
	    });
	}

	/**
	 * Filtro para Buscar documento existente
	 * @param documentosExistentes
	 * @param novoDocumento
	 * @return
	 */
	private Documento filtrarDocumentosExistentes(List<Documento> documentosExistentes, Documento novoDocumento) {
		Documento documentoExistente = documentosExistentes.stream()
		        .filter(d -> d.getTipoDocumento().equals(novoDocumento.getTipoDocumento()))
		        .findFirst()
		        .orElse(null);
		return documentoExistente;
	}

	/**
	 * Adiciona novo documento
	 * @param beneficiarioExistente
	 * @param documentosExistentes
	 * @param novoDocumento
	 */
	private void criarDocumentoNovo(Beneficiario beneficiarioExistente, List<Documento> documentosExistentes, Documento novoDocumento) {
		novoDocumento.setBeneficiario(beneficiarioExistente);
		documentoRepository.save(novoDocumento);
		documentosExistentes.add(novoDocumento);
	}

	/**
	 * Remove documentos que foram removidos da lista nova e do banco de dados
	 * @param documentosExistentes
	 * @param tiposDocumentosNovos
	 */
	private void excluirDocumentosRemovidos(List<Documento> documentosExistentes, Set<String> tiposDocumentosNovos) {
	    Iterator<Documento> iterator = documentosExistentes.iterator();
	    while (iterator.hasNext()) {
	        Documento documento = iterator.next();
	        if (!tiposDocumentosNovos.contains(documento.getTipoDocumento())) {
	            documentoRepository.delete(documento); // Remove do banco de dados
	            iterator.remove(); // Remove da lista de documentos do beneficiário
	        }
	    }
	}


	/**
	 *  Cria um conjunto para armazenar os tipos de documentos da lista nova
	 * @param novosDocumentos
	 * @return
	 */
	private Set<String> criarSetDocumentosNovos(List<Documento> novosDocumentos) {
	    Set<String> tiposDocumentosNovos = novosDocumentos.stream()
	            .map(Documento::getTipoDocumento)
	            .collect(Collectors.toSet());
		return tiposDocumentosNovos;
	}
}
