package com.system.pauta.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PautaInput {
	
	@NotBlank
	private String descricao;

}
