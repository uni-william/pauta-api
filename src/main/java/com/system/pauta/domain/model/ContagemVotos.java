package com.system.pauta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ContagemVotos {

	private Voto voto;
	private Long quantidade;
}
