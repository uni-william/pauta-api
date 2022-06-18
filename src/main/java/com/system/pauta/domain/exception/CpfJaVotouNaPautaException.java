package com.system.pauta.domain.exception;

public class CpfJaVotouNaPautaException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public CpfJaVotouNaPautaException(String mensagem) {
		super(mensagem);
	}
	
	public CpfJaVotouNaPautaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		
	}

}
