package com.system.pauta.api.model;

import java.util.List;

import com.system.pauta.domain.model.ContagemVotos;

import lombok.Data;

@Data
public class EstatisticaVotacao {
	
	private Long idPauta;
	private String descricao;
	private String situacao;
	private List<ContagemVotos> votos;

}
