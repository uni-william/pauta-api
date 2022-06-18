package com.system.pauta.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.pauta.domain.model.Votacao;

public interface VotacaoRepository extends JpaRepository<Votacao, Long>, VotacaoRepositpryQuery {
	
	Votacao findByPautaIdAndCpf(Long idPauta, String cpf);

}
