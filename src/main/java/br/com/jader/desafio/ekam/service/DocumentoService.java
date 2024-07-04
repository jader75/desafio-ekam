package br.com.jader.desafio.ekam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jader.desafio.ekam.domain.Beneficiario;
import br.com.jader.desafio.ekam.domain.Documento;
import br.com.jader.desafio.ekam.exception.BeneficiarioNaoEncontradoException;
import br.com.jader.desafio.ekam.exception.DocumentoNaoEncontradoException;
import br.com.jader.desafio.ekam.exception.DocumentoSemBeneficiarioException;
import br.com.jader.desafio.ekam.repository.BeneficiarioRepository;
import br.com.jader.desafio.ekam.repository.DocumentoRepository;

@Service
public class DocumentoService {

	@Autowired
	private DocumentoRepository documentoRepository;

	@Autowired
	private BeneficiarioRepository beneficiarioRepository;
	
	@Transactional
	public Documento cadastrarDocumento(Documento documento) {
	    Long beneficiarioId = documento.getBeneficiario() != null ? documento.getBeneficiario().getId() : null;

	    if (beneficiarioId != null) {
	        Beneficiario beneficiario = beneficiarioRepository.findById(beneficiarioId)
	                .orElseThrow(() -> new BeneficiarioNaoEncontradoException("Beneficiario não encontrado com o ID: " + beneficiarioId));
	        documento.setBeneficiario(beneficiario);
	    } else {
	        throw new DocumentoSemBeneficiarioException("ID do beneficiário não pode ser nulo.");
	    }

	    return documentoRepository.save(documento);
	}

	@Transactional(readOnly = true)
	public List<Documento> listarDocumentos() {
		return documentoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Documento buscarDocumentoPorId(Long id) {
		return documentoRepository.findById(id)
				.orElseThrow(() -> new DocumentoNaoEncontradoException("Documento não encontrado com o ID: " + id));
	}

	@Transactional
	public Documento atualizarDocumento(Documento documento) {
		// Verifique se o documento existe antes de atualizar
		if (documento.getId() == null || !documentoRepository.existsById(documento.getId())) {
			throw new DocumentoNaoEncontradoException("Documento não encontrado");
		}
		return documentoRepository.save(documento);
	}

	@Transactional
	public void removerDocumento(Long id) {
		documentoRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Documento> listarDocumentosPorBeneficiario(Long beneficiarioId) {
		return documentoRepository.findByBeneficiarioId(beneficiarioId);
	}
}
