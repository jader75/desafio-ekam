package br.com.jader.desafio.ekam.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jader.desafio.ekam.domain.Beneficiario;
import br.com.jader.desafio.ekam.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/beneficiarios")
public class BeneficiarioController {

    @Autowired
    private BeneficiarioService beneficiarioService;

    @PostMapping
    @Operation(summary = "Cadastrar um beneficiário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Beneficiario> cadastrarBeneficiario(
            @Valid @RequestBody Beneficiario beneficiario) {
        Beneficiario novoBeneficiario = beneficiarioService.cadastrarBeneficiario(beneficiario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBeneficiario);
    }

    @GetMapping
    @Operation(summary = "Listar todos os beneficiários")
    @ApiResponse(responseCode = "200", description = "Lista de beneficiários recuperada com sucesso")
    public ResponseEntity<List<Beneficiario>> listarBeneficiarios() {
        List<Beneficiario> beneficiarios = beneficiarioService.listarBeneficiarios();
        return ResponseEntity.ok().body(beneficiarios);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar beneficiário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário encontrado"),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado")
    })
    public ResponseEntity<Beneficiario> buscarBeneficiarioPorId(
            @Parameter(description = "ID do beneficiário a ser buscado", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok( beneficiarioService.buscarBeneficiarioPorId(id) );
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados de um beneficiário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado")
    })
    public ResponseEntity<Beneficiario> atualizarBeneficiario(
            @Parameter(description = "ID do beneficiário a ser atualizado", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Beneficiario beneficiario) {
        beneficiario.setId(id);
        Beneficiario beneficiarioAtualizado = beneficiarioService.atualizarBeneficiario(beneficiario);
        return ResponseEntity.ok().body(beneficiarioAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um beneficiário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beneficiário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado")
    })
    public ResponseEntity<Void> removerBeneficiario(
            @Parameter(description = "ID do beneficiário a ser removido", required = true)
            @PathVariable Long id) {
        beneficiarioService.removerBeneficiario(id);
        return ResponseEntity.noContent().build();
    }
}
