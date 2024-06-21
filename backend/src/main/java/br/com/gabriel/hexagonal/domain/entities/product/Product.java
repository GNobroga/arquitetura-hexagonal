package br.com.gabriel.hexagonal.domain.entities.product;

import java.math.BigDecimal;

import br.com.gabriel.hexagonal.domain.entities.validation.ValidationHandler;
import br.com.gabriel.hexagonal.domain.entities.validation.ValidationHandler.Validation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product implements Validation, Cloneable {
    
    private Long id;

    private String name;

    private String description;

    private boolean available;

    private BigDecimal price;

    private String imageUrl;


    private Product() {}

    @Override
    public void validate(ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch(Throwable error) {
            return new Product();
        }
    }
}
