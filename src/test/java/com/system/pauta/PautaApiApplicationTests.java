package com.system.pauta;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.system.pauta.domain.model.Pauta;
import com.system.pauta.domain.service.PautaService;

@SpringBootTest
class PautaApiApplicationTests {

	@Autowired
	private PautaService pautaService;

	@Test
	public void testarCadastroPauta() {

		// cenário
		Pauta pauta = new Pauta();
		pauta.setDescricao("A copa de 2022 será vencida pelo Brasil");

		// ação
		pauta = pautaService.salvar(pauta);

		// validação
		assertThat(pauta.getId()).isNotNull();

	}

	@Test
	public void testarPautaSemDescricao() {
		// Teste em um cenário de falha
		
		// cenário
		Pauta pauta = new Pauta();
		pauta.setDescricao(null);
		
		// ação
		ConstraintViolationException erroEsperado = Assertions.assertThrows(ConstraintViolationException.class, () -> {
			pautaService.salvar(pauta);
		});
		
		// validação
		assertThat(erroEsperado).isNotNull();

	}

}
