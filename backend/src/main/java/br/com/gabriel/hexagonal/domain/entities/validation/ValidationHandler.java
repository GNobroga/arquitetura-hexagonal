package br.com.gabriel.hexagonal.domain.entities.validation;
import java.util.List;

public interface ValidationHandler {
    
    public interface Validation {
        void validate(ValidationHandler handler);
    }

    void addError(Error error);

    void addErrors(List<Error> errors);

    boolean hasError();

    List<Error> getErrors();
}
