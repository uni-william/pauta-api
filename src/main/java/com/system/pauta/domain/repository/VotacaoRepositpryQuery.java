package com.system.pauta.domain.repository;

import java.util.List;

import com.system.pauta.domain.model.ContagemVotos;

public interface VotacaoRepositpryQuery {
	
	List<ContagemVotos> contagemVotosPorPauta(Long idPauta);
}
