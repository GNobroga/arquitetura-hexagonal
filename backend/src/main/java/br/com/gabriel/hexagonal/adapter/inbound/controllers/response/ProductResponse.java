package br.com.gabriel.hexagonal.adapter.inbound.controllers.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.gabriel.hexagonal.domain.entities.product.Product;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductResponse(
    Long id,
    String name,
    String description,
    boolean available,
    BigDecimal price,
    String imageUrl
) {
    
    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getId(), 
            product.getName(), 
            product.getDescription(), 
            product.isAvailable(), 
            product.getPrice(), 
            product.getImageUrl()
        );
    }
}
