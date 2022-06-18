package com.system.pauta.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.pauta.api.model.PautaModel;
import com.system.pauta.api.model.input.PautaInput;
import com.system.pauta.domain.model.Pauta;

@Component
public class PautaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Pauta toDomainObject(PautaInput pautaInput) {
		return modelMapper.map(pautaInput, Pauta.class);
	}
	
	public PautaModel toModel(Pauta pauta) {
		return modelMapper.map(pauta, PautaModel.class);
	}
	
	public List<PautaModel> toCollectionModel(List<Pauta> pautas) {
		return pautas.stream()
				.map(pauta -> toModel(pauta))
				.collect(Collectors.toList());
	}
	
	
}
