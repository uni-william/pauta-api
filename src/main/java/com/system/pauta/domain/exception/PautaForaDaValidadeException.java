package com.system.pauta.domain.exception;

public class PautaForaDaValidadeException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
	
	public PautaForaDaValidadeException(String mensagem) {
		super(mensagem);
	}
	
	public PautaForaDaValidadeException(String mensagem, Throwable causa) {
		super(mensagem, causa);
		
	}

}
