package br.com.gabriel.hexagonal.adapter.inbound.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabriel.hexagonal.adapter.inbound.controllers.response.ProductResponse;
import br.com.gabriel.hexagonal.domain.inbound.FindAllProductUseCasePort;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import br.com.gabriel.hexagonal.domain.model.SearchCriteria;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController  implements ProductAPI {

    private FindAllProductUseCasePort findAllProductUseCase;
    
    @Override
    @GetMapping
    public Pagination<ProductResponse> list(
        final String term, 
        final int page,
        final int size,
        final String sort
    ) {
        final var pagination = findAllProductUseCase.execute(SearchCriteria.with(term, sort, size, page));
        final var mappedItems = pagination.getItems().stream().map(ProductResponse::with).toList();
        return Pagination.with(pagination.getSize(), pagination.getPage(), pagination.getTotalElements(), mappedItems);
    }   
}
