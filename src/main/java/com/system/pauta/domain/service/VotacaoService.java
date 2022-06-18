package com.system.pauta.domain.service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.system.pauta.api.mapper.VotacaoMapper;
import com.system.pauta.api.model.EstatisticaVotacao;
import com.system.pauta.api.model.ValidarCpfModel;
import com.system.pauta.api.model.VotoModel;
import com.system.pauta.api.model.input.VotacaoInput;
import com.system.pauta.domain.exception.CpfJaVotouNaPautaException;
import com.system.pauta.domain.exception.PautaForaDaValidadeException;
import com.system.pauta.domain.exception.PautaNaoEncontradaException;
import com.system.pauta.domain.model.ContagemVotos;
import com.system.pauta.domain.model.Pauta;
import com.system.pauta.domain.model.Votacao;
import com.system.pauta.domain.model.Voto;
import com.system.pauta.domain.repository.PautaRepository;
import com.system.pauta.domain.repository.VotacaoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VotacaoService {

	@Autowired
	private VotacaoRepository votacaoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private VotacaoMapper votacaoMapper;

	private static final String RESOURCE_PATH = "https://user-info.herokuapp.com/users/";

	public VotoModel voto(VotacaoInput input, Voto voto) {
		try {
			log.info("Registrando voto");
			salvarVoto(input, voto);
			return new VotoModel(Boolean.TRUE, "Voto contabilizado com sucesso");
		} catch (PautaNaoEncontradaException e) {
			log.error("Pauta não encontrada para este id " + input.getPauta().getId().toString());
			return new VotoModel(Boolean.FALSE,
					"Pauta não encontrada para este id " + input.getPauta().getId().toString());		
		} catch (PautaForaDaValidadeException e) {
			log.error("Pauta fora da validade para votação");
			return new VotoModel(Boolean.FALSE, "Pauta fora da validade para votação");
		} catch (CpfJaVotouNaPautaException e) {
			log.error("CPF já votou para esta pauta");
			return new VotoModel(Boolean.FALSE, "CPF já votou para esta pauta");
		}
	}

	public EstatisticaVotacao estatisticaVotacao(Long idPauta) {

		Optional<Pauta> optionalPauta = pautaRepository.findById(idPauta);

		if (!optionalPauta.isPresent())
			throw new PautaNaoEncontradaException("Pauta não encontrada");

		EstatisticaVotacao estatisticaVotacao = new EstatisticaVotacao();
		log.info("Carregando dados da pauta");
		Pauta pauta = optionalPauta.get();
		estatisticaVotacao.setIdPauta(pauta.getId());
		estatisticaVotacao.setDescricao(pauta.getDescricao());

		if (LocalDateTime.now().isAfter(pauta.getDataValidade()))
			estatisticaVotacao.setSituacao("Votação finalizada");
		else
			estatisticaVotacao.setSituacao("Votação em andamento");

		log.info("Carregando os votos da pauta");
		estatisticaVotacao.setVotos(contagemVotos(idPauta));

		return estatisticaVotacao;
	}

	public ValidarCpfModel validarCpf(String cpf) {
		URI uri = java.net.URI.create(RESOURCE_PATH + cpf);	
		RestTemplate restTemplate = new RestTemplate();
		ValidarCpfModel validarCpfModel = restTemplate.getForObject(uri, ValidarCpfModel.class);
		return validarCpfModel;
	}

	private List<ContagemVotos> contagemVotos(Long idPauta) {
		List<ContagemVotos> votosConsulta = votacaoRepository.contagemVotosPorPauta(idPauta);
		List<ContagemVotos> votosRetorno = new ArrayList<>();
		boolean possuiVotoSim = false;
		boolean possuiVotoNao = false;
		for (ContagemVotos contagemVotos : votosConsulta) {
			votosRetorno.add(contagemVotos);
			if (contagemVotos.getVoto().equals(Voto.SIM))
				possuiVotoSim = true;
			if (contagemVotos.getVoto().equals(Voto.NAO))
				possuiVotoNao = true;
		}
		if (!possuiVotoSim)
			votosRetorno.add(new ContagemVotos(Voto.SIM, 0l));
		if (!possuiVotoNao)
			votosRetorno.add(new ContagemVotos(Voto.NAO, 0l));

		return votosRetorno;
	}

	private void salvarVoto(VotacaoInput input, Voto voto) {
		log.info("Validando se pauta existe");
		if (pautaNaoEncotnrada(input.getPauta().getId()))
			throw new PautaNaoEncontradaException("Pauta não encontrada");
		log.info("Validando se pauta está valida para votação");
		if (pautaForaDaValidade(input.getPauta().getId()))
			throw new PautaForaDaValidadeException("Pauta está fora da validade para votação");
		log.info("Validando se CPF já não votou na pauta");
		if (cpfJaVotouPauta(input.getPauta().getId(), input.getCpf()))
			throw new CpfJaVotouNaPautaException("CPF já votou para essa pauta");

		Votacao votacao = votacaoMapper.toDomainObject(input);
		votacao.setVoto(voto);
		votacaoRepository.save(votacao);

	}

	private boolean cpfJaVotouPauta(Long idPauta, String cpf) {
		Votacao votacao = votacaoRepository.findByPautaIdAndCpf(idPauta, cpf);
		return votacao != null;
	}

	private boolean pautaNaoEncotnrada(Long idPauta) {
		Optional<Pauta> optional = pautaRepository.findById(idPauta);
		return !optional.isPresent();
	}

	private boolean pautaForaDaValidade(Long idPauta) {
		Optional<Pauta> optional = pautaRepository.findById(idPauta);
		Pauta pauta = optional.get();
		LocalDateTime dataAtual = LocalDateTime.now();
		return dataAtual.isAfter(pauta.getDataValidade());

	}

}
