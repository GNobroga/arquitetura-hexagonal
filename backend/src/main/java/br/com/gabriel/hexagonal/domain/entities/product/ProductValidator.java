package br.com.gabriel.hexagonal.domain.entities.product;

import br.com.gabriel.hexagonal.domain.entities.validation.Error;
import br.com.gabriel.hexagonal.domain.entities.validation.ValidationHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductValidator  {

    private final Product product;
    private final ValidationHandler handler;

    public void validate() {
       checkName();
       checkDescription();
    }

    public void checkName() {
        final var name = product.getName();
        if (name == null || name.isBlank()) {
            handler.addError(new Error("O nome do produto é obrigatório."));
            return;
        }

        if (name.length() <= 3) {
            handler.addError(new Error("O nome precisa ter mais de 3 caracteres."));
        }

        if (name.length() > 100) {
            handler.addError(new Error("O nome não pode ter mais que 100 caracteres."));
        }
    }

    public void checkDescription() {
        final var description = product.getDescription();
        if (description == null || description.isBlank()) {
            handler.addError(new Error("A descrição do produto é obrigatória."));
            return;
        }

        if (description.length() <= 10) {
            handler.addError(new Error("A descrição deve ter mais de 10 caracteres."));
        }

        if (description.length() > 255) {
            handler.addError(new Error("A descrição não pode ter mais que 255 caracteres."));
        }
    }
    
}
