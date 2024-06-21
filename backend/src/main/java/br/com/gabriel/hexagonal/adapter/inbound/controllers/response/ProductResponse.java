package br.com.gabriel.hexagonal.adapter.inbound.controllers.response;

import br.com.gabriel.hexagonal.domain.entities.product.Product;

public record ProductResponse(
    Long id,
    String name,
    String description,
    boolean available
) {
    
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.isAvailable());
    }
}
