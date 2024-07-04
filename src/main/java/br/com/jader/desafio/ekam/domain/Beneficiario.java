package br.com.jader.desafio.ekam.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "beneficiarios")
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;

    @Schema(description = "Nome do beneficiário")
    private String nome;

    @Schema(description = "Telefone do beneficiário")
    private String telefone;

    @Column(name = "data_nascimento")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento do beneficiário")
    private LocalDate dataNascimento;

    @Column(name = "data_inclusao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Data de inclusão do beneficiário no plano")
    @Hidden
    private LocalDateTime dataInclusao;

    @Column(name = "data_atualizacao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Data de atualização do documento")
    @Hidden
    private LocalDateTime dataAtualizacao;
    
    @Column(name = "data_exclusao")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Schema(description = "Data de exclusão do beneficiário do plano")
    @Hidden
    private LocalDateTime dataExclusao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "beneficiario")
    private List<Documento> documentos;
    
    @PrePersist
    protected void onCreate() {
        dataInclusao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
    
    @PreRemove
    protected void onRemove() {
        dataExclusao = LocalDateTime.now();
    }
    
}