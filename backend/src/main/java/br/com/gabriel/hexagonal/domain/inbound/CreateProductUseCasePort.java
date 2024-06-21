package br.com.gabriel.hexagonal.domain.inbound;

import br.com.gabriel.hexagonal.domain.entities.product.Product;

public interface CreateProductUseCasePort {
    Product execute(final Product aProduct);
}
