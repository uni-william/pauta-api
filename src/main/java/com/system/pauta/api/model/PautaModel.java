package com.system.pauta.api.model;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PautaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	@ApiModelProperty(example = "Pauta sobre Métodos Ágeis")
	private String descricao;
	private LocalDateTime dataValidade;

}
