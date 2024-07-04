package br.com.jader.desafio.ekam.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
//
//    @Autowired
//    private DocumentoService documentoService;
//
////    @PostMapping
////    @Operation(summary = "Cadastrar um documento")
////    @ApiResponses(value = {
////            @ApiResponse(responseCode = "201", description = "Documento cadastrado com sucesso"),
////            @ApiResponse(responseCode = "400", description = "Requisição inválida")
////    })
////    public ResponseEntity<Documento> cadastrarDocumento(
////            @Valid @RequestBody Documento documento) {
////        Documento novoDocumento = documentoService.cadastrarDocumento(documento);
////        return ResponseEntity.status(HttpStatus.CREATED).body(novoDocumento);
////    }
//
//    @GetMapping
//    @Operation(summary = "Listar todos os documentos")
//    @ApiResponse(responseCode = "200", description = "Lista de documentos recuperada com sucesso")
//    public ResponseEntity<List<Documento>> listarDocumentos() {
//        List<Documento> documentos = documentoService.listarDocumentos();
//        return ResponseEntity.ok().body(documentos);
//    }
//
//    @GetMapping("/{id}")
//    @Operation(summary = "Buscar documento por ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Documento encontrado"),
//            @ApiResponse(responseCode = "404", description = "Documento não encontrado")
//    })
//    public ResponseEntity<Documento> buscarDocumentoPorId(
//            @Parameter(description = "ID do documento a ser buscado", required = true)
//            @PathVariable Long id) {
//        return ResponseEntity.ok( documentoService.buscarDocumentoPorId(id) );
//    }
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Atualizar dados de um documento")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Documento atualizado com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
//            @ApiResponse(responseCode = "404", description = "Documento não encontrado")
//    })
//    public ResponseEntity<Documento> atualizarDocumento(
//            @Parameter(description = "ID do documento a ser atualizado", required = true)
//            @PathVariable Long id,
//            @Valid @RequestBody DocumentoBase documentoBase) {
//        documentoBase.setId(id);
//        Documento documento = new Documento(documentoBase);
//        Documento documentoAtualizado = documentoService.atualizarDocumento(documento);
//        return ResponseEntity.ok().body(documentoAtualizado);
//    }
//
//    @DeleteMapping("/{id}")
//    @Operation(summary = "Remover um documento")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "Documento removido com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Documento não encontrado")
//    })
//    public ResponseEntity<Void> removerDocumento(
//            @Parameter(description = "ID do documento a ser atualizado", required = true)
//            @PathVariable Long id) {
//        documentoService.removerDocumento(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/beneficiario/{beneficiarioId}")
//    @Operation(summary = "Listar documentos por ID do beneficiário")
//    @ApiResponse(responseCode = "200", description = "Lista de documentos do beneficiário recuperada com sucesso")
//    public ResponseEntity<List<Documento>> listarDocumentosPorBeneficiario(
//            @Parameter(description = "ID do beneficiário para listar documentos", required = true)
//            @PathVariable Long beneficiarioId) {
//        List<Documento> documentos = documentoService.listarDocumentosPorBeneficiario(beneficiarioId);
//        return ResponseEntity.ok().body(documentos);
//    }
}
