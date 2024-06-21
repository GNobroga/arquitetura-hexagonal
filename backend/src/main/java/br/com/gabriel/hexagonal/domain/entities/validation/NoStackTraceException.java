package br.com.gabriel.hexagonal.domain.entities.validation;

public class NoStackTraceException extends RuntimeException {
    
    public NoStackTraceException(String message) {
        super(message, null, true, false);
    }
}
