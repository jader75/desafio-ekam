package br.com.jader.desafio.ekam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jader.desafio.ekam.domain.Beneficiario;

@Repository
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {

    List<Beneficiario> findByNomeContainingIgnoreCase(String nome);

}