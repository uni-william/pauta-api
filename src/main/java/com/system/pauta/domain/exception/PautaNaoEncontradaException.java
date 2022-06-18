package com.system.pauta.domain.exception;

public class PautaNaoEncontradaException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public PautaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PautaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		
	}

}
