package com.system.pauta.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.pauta.api.mapper.PautaMapper;
import com.system.pauta.api.model.PautaModel;
import com.system.pauta.api.model.input.PautaInput;
import com.system.pauta.domain.model.Pauta;
import com.system.pauta.domain.repository.PautaRepository;
import com.system.pauta.domain.service.PautaService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/pautas")
public class PautaController {

	@Autowired
	private PautaService pautaService;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private PautaMapper pautaMapper;

	@GetMapping
	public List<PautaModel> listar() {
		return pautaMapper.toCollectionModel(pautaRepository.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PautaModel adicionar(@RequestBody @Valid PautaInput pautaInput) {
		Pauta pauta = pautaMapper.toDomainObject(pautaInput);
		return pautaMapper.toModel(pautaService.salvar(pauta));
	}

	@PutMapping("/atualizarDataValidade")
	@ResponseStatus(HttpStatus.OK)
	public PautaModel atualizarDataValidade(
			@RequestParam(required = true)
			@ApiParam(value = "ID da pauta", example = "1") Long idPauta,
			@RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
			@ApiParam(value = "Nova data de validade em formato iso, exemplo: 2022-06-17T20:30:35") LocalDateTime novaData) {
		Pauta pauta = pautaService.atualizarDataValidade(idPauta, novaData);
		return pautaMapper.toModel(pauta);
	}

}
