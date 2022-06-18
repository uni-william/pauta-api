package com.system.pauta.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.pauta.api.model.input.VotacaoInput;
import com.system.pauta.domain.model.Votacao;

@Component
public class VotacaoMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Votacao toDomainObject(VotacaoInput votacaoInput) {
		return modelMapper.map(votacaoInput, Votacao.class);
	}

}
