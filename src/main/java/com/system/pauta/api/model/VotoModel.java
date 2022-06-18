package com.system.pauta.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VotoModel {

	@ApiModelProperty(value = "Retorna se o voto foi contabilidado ou não")
	private Boolean resultado;
	@ApiModelProperty(value = "Mensagem de sucesso ou erro")
	private String mensagem;
}
