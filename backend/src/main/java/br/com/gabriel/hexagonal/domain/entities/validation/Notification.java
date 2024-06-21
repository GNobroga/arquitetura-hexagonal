package br.com.gabriel.hexagonal.domain.entities.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Notification implements ValidationHandler {
    
    private final List<Error> errors = new ArrayList<>();

    @Override
    public void addError(Error error) {
       errors.add(error);
    }

    @Override
    public void addErrors(List<Error> errors) {
       this.errors.addAll(errors);
    }

    @Override
    public boolean hasError() {
        return !errors.isEmpty();
    }

    @Override
    public List<Error> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
