package br.com.jader.desafio.ekam.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Documento extends DocumentoBase {

    @Column(name = "data_inclusao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Data de inclusão do documento", example = "04/10/2024 10:00:00")
    private LocalDateTime dataInclusao;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Data de atualização do documento", example = "04/10/2024 10:00:00")
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false)
    private Beneficiario beneficiario;

    @PrePersist
    protected void onCreate() {
        dataInclusao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }

    @JsonProperty("beneficiario{Id}")
    @Schema(description = "ID do beneficiário")
    public Long getBeneficiario_Id() {
        return beneficiario != null ? beneficiario.getId() : null;
    }

    public Documento(DocumentoBase  base) {
    	super();
    	this.setId(base.getId());
    	this.setTipoDocumento(base.getTipoDocumento());
    	this.setDescricao(base.getDescricao());
    }
}
