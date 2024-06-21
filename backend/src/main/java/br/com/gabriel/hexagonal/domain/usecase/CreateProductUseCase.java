package br.com.gabriel.hexagonal.domain.usecase;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import br.com.gabriel.hexagonal.domain.entities.validation.DomainInvalidException;
import br.com.gabriel.hexagonal.domain.entities.validation.Notification;
import br.com.gabriel.hexagonal.domain.inbound.CreateProductUseCasePort;
import br.com.gabriel.hexagonal.domain.outbound.ProductServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateProductUseCase implements CreateProductUseCasePort {

    private final ProductServicePort service;

    @Override
    public Product execute(final Product aProduct) {
        final var notification = new Notification();
        aProduct.validate(notification);

        if (notification.hasError()) {
            throw new DomainInvalidException(notification.getErrors());
        }

        return service.create(aProduct);
    }
    
}
