package br.com.jader.desafio.ekam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jader.desafio.ekam.domain.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

	List<Documento> findByTipoDocumento(String tipoDocumento);

	List<Documento> findByBeneficiarioId(Long beneficiarioId);

}