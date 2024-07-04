package br.com.jader.desafio.ekam.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.jader.desafio.ekam.exception.BeneficiarioNaoEncontradoException;
import br.com.jader.desafio.ekam.exception.DocumentoNaoEncontradoException;
import br.com.jader.desafio.ekam.exception.DocumentoSemBeneficiarioException;

@Aspect
@Component
public class ExceptionHandlerAspect {

    @Pointcut("execution(* br.com.jader.desafio.ekam.services..*.*(..)) || execution(* br.com.jader.desafio.ekam.controllers..*.*(..))")
    public void allServiceAndControllerMethods() {}

    @AfterThrowing(pointcut = "allServiceAndControllerMethods()", throwing = "ex")
    public ResponseEntity<String> handleException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = "Ocorreu um erro durante o processamento da requisição.";

        if (ex instanceof BeneficiarioNaoEncontradoException) {
            status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        } else if (ex instanceof DocumentoNaoEncontradoException) {
            status = HttpStatus.NOT_FOUND;
            message = ex.getMessage();
        } else if (ex instanceof DocumentoSemBeneficiarioException) {
            status = HttpStatus.BAD_REQUEST;
            message = ex.getMessage();
        }

        return ResponseEntity.status(status).body(message);
    }
}
