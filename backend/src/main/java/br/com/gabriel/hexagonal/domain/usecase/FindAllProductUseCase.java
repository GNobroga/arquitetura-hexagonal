package br.com.gabriel.hexagonal.domain.usecase;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import br.com.gabriel.hexagonal.domain.inbound.FindAllProductUseCasePort;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import br.com.gabriel.hexagonal.domain.model.SearchCriteria;
import br.com.gabriel.hexagonal.domain.outbound.ProductServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FindAllProductUseCase implements FindAllProductUseCasePort {

    private final ProductServicePort service;

    @Override
    public Pagination<Product> execute(SearchCriteria criteria) {
        return service.findAll(criteria);
    }
    
}
