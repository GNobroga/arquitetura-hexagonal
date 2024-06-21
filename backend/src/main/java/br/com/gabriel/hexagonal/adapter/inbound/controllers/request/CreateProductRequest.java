package br.com.gabriel.hexagonal.adapter.inbound.controllers.request;

import org.hibernate.validator.constraints.Length;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
    @NotBlank(message = "nome é obrigatório.")
    @Length(min = 3, max = 255, message = "nome deve estar entre 10 a 100 caracteres.")
    String name,

    @NotBlank(message = "descrição é obrigatório.")
    @Length(min = 10, max = 255, message = "descrição deve estar entre 10 a 255 caracteres.")
    String description,

    @NotNull(message = "disponivel é obrigatório")
    Boolean available
) {

    public Product toProduct() {
        return new Product(null, name(), description(), available());
    }
    
}
