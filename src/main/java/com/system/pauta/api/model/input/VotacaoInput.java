package com.system.pauta.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotacaoInput {
	
	@CPF
	@NotBlank
	private String cpf;
	
	@Valid
	@NotNull
	private PautaIdInput pauta;

}
