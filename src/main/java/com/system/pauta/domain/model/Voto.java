package com.system.pauta.domain.model;

import lombok.Getter;

public enum Voto {
	
	SIM("Sim"),
	NAO("Não");

	@Getter
	private String descricao;

	private Voto(String descricao) {
		this.descricao = descricao;
	}
}
