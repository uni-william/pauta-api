package com.system.pauta.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	@ApiModelProperty(example = "http://cwi.com.br/dados-invalidos", position = 5)
	private String type;
	@ApiModelProperty(example = "Dados inválidos", position = 10)
	private String title;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 15)
	private String detail;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
	private String userMessage;
	@ApiModelProperty(example = "2021-10-05T14:38:51.2233455-04:00", position = 25)
	private OffsetDateTime timestamp;
	@ApiModelProperty(value = "Campos que geraram o erro", position = 30)
	private List<Field> fields;
	
	@Getter
	@Builder
	public static class Field {
		
		private String name;
		private String userMessage;
		
	}
	
}
