package br.com.jader.desafio.ekam.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "documentos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class DocumentoBase {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Schema(description = "Tipo do documento")
    private String tipoDocumento;

    @Schema(description = "Descrição do documento")
    private String descricao;

}