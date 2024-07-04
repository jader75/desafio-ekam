package br.com.jader.desafio.ekam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DocumentoSemBeneficiarioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DocumentoSemBeneficiarioException(String message) {
        super(message);
    }
}
