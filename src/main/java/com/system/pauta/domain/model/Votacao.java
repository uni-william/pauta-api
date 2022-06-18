package com.system.pauta.domain.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "votacao_pauta")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Votacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@CPF
	private String cpf;

	@Enumerated(EnumType.STRING)
	private Voto voto;

	@ManyToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;

}
