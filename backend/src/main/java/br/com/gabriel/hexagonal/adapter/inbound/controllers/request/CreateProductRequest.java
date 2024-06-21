package br.com.gabriel.hexagonal.adapter.inbound.controllers.request;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
    @NotBlank(message = "nome é obrigatório.")
    @Length(min = 3, max = 255, message = "nome deve estar entre 10 a 100 caracteres.")
    String name,

    @Length(max = 255, message = "imagem url pode conter no máximo 255 caractres.")
    String imageUrl,

    @Min(value = 0, message = "preço não pode ser menor que zero")
    @NotNull(message = "preço é obrigatório")
    BigDecimal price,

    @NotBlank(message = "descrição é obrigatório.")
    @Length(min = 10, max = 255, message = "descrição deve estar entre 10 a 255 caracteres.")
    String description,

    @NotNull(message = "disponivel é obrigatório")
    Boolean available
) {

    public Product toProduct() {
        return new Product(null, name(), description(), available(), price(), imageUrl());
    }
    
}
