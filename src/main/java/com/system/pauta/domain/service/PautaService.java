package com.system.pauta.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.pauta.domain.exception.PautaNaoEncontradaException;
import com.system.pauta.domain.model.Pauta;
import com.system.pauta.domain.repository.PautaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PautaService {
	
	@Value(value = "${pauta.tempo-validade}")
	private Long validade;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Transactional
	public Pauta salvar(Pauta pauta) {
		log.info("Setando data de validade com base na data atual, mas o tempo definido no application.properties");
		LocalDateTime data = LocalDateTime.now().plusMinutes(validade);
		pauta.setDataValidade(data);
		log.info("Salvando pauta");
		return pautaRepository.save(pauta);
	}
	
	@Transactional
	public Pauta atualizarDataValidade(Long idPauta, LocalDateTime novaData) {
		Optional<Pauta> optionalPauta = pautaRepository.findById(idPauta);
		if (optionalPauta.isPresent()) {
			Pauta pauta = optionalPauta.get();
			pauta.setDataValidade(novaData);
			log.info("Atualizando data da pauta");
			return pautaRepository.save(pauta);
		} else 
			log.error("Lançando erro de pauta não encontrada");
			throw new PautaNaoEncontradaException("Pauta não encontrada");
	
	}

}
