package com.system.pauta.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.system.pauta.api.model.EstatisticaVotacao;
import com.system.pauta.api.model.VotoModel;
import com.system.pauta.api.model.input.VotacaoInput;
import com.system.pauta.domain.model.Voto;
import com.system.pauta.domain.service.VotacaoService;

@RestController
@RequestMapping("/votos")
public class VotacaoController {
	
	@Autowired
	private VotacaoService votacaoService;
	
	@PostMapping("/sim")
	public ResponseEntity<?> votarSim(@Valid @RequestBody VotacaoInput voto) {
		VotoModel votoModel = votacaoService.voto(voto, Voto.SIM);
		if (Boolean.TRUE.equals(votoModel.getResultado()))
			return ResponseEntity.status(HttpStatus.CREATED).body(votoModel);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(votoModel);
	}
	
	@PostMapping("/nao")
	public ResponseEntity<?> votarNao(@Valid @RequestBody VotacaoInput voto) {
		VotoModel votoModel = votacaoService.voto(voto, Voto.NAO);
		if (Boolean.TRUE.equals(votoModel.getResultado()))
			return ResponseEntity.status(HttpStatus.CREATED).body(votoModel);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(votoModel);
	}
	
	@GetMapping("/contagemVotos/{idPauta}")
	@ResponseStatus(HttpStatus.OK)
	public EstatisticaVotacao estatisticaVotacao(@PathVariable Long idPauta) {
		return votacaoService.estatisticaVotacao(idPauta);
	}
	
	@GetMapping("/validarCpf/{cpf}")
	public ResponseEntity<?> validarCpf(@PathVariable String cpf) {
		try {
			return ResponseEntity.ok(votacaoService.validarCpf(cpf));
		} catch (org.springframework.web.client.HttpClientErrorException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
