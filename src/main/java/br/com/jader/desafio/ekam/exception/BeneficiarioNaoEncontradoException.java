package br.com.jader.desafio.ekam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeneficiarioNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BeneficiarioNaoEncontradoException(String message) {
        super(message);
    }
}
