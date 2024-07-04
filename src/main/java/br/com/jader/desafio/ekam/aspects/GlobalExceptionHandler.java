package br.com.jader.desafio.ekam.aspects;

import br.com.jader.desafio.ekam.exception.BeneficiarioNaoEncontradoException;
import br.com.jader.desafio.ekam.exception.DocumentoNaoEncontradoException;
import br.com.jader.desafio.ekam.exception.DocumentoSemBeneficiarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BeneficiarioNaoEncontradoException.class)
    public ResponseEntity<String> handleBeneficiarioNaoEncontradoException(BeneficiarioNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DocumentoNaoEncontradoException.class)
    public ResponseEntity<String> handleDocumentoNaoEncontradoException(DocumentoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DocumentoSemBeneficiarioException.class)
    public ResponseEntity<String> handleDocumentoSemBeneficiarioException(DocumentoSemBeneficiarioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado.");
    }
}
