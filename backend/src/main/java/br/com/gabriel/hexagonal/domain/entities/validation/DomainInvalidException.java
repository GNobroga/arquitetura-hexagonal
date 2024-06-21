package br.com.gabriel.hexagonal.domain.entities.validation;

import java.util.List;

import lombok.Getter;

@Getter
public class DomainInvalidException extends NoStackTraceException {
    
    private List<Error> errors;

    public DomainInvalidException(List<Error> errors) {
        super("Domain Invalid");
        this.errors = errors;
    }

}
