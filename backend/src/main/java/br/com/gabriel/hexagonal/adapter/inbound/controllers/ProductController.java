package br.com.gabriel.hexagonal.adapter.inbound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabriel.hexagonal.adapter.inbound.controllers.request.CreateProductRequest;
import br.com.gabriel.hexagonal.adapter.inbound.controllers.response.ProductResponse;
import br.com.gabriel.hexagonal.domain.inbound.CreateProductUseCasePort;
import br.com.gabriel.hexagonal.domain.inbound.DeleteProductByIdUseCasePort;
import br.com.gabriel.hexagonal.domain.inbound.FindAllProductUseCasePort;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import br.com.gabriel.hexagonal.domain.model.SearchCriteria;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController  implements ProductAPI {

    private final FindAllProductUseCasePort findAllProductUseCase;

    private final CreateProductUseCasePort createProductUseCase;

    private final DeleteProductByIdUseCasePort deleteProductByIdUseCase;
    
    @Override
    public Pagination<ProductResponse> list(
        final String term, 
        final int page,
        final int size,
        final String sort
    ) {
        final var pagination = findAllProductUseCase.execute(SearchCriteria.with(term, sort, size, page));
        final var mappedItems = pagination.getItems().stream().map(ProductResponse::from).toList();
        return Pagination.with(pagination.getSize(), pagination.getPage(), pagination.getTotalElements(), mappedItems);
    }

    @Override
    public ResponseEntity<ProductResponse> create(@Valid final CreateProductRequest input) {
        final var savedProduct = createProductUseCase.execute(input.toProduct());
        return ResponseEntity.created(null).body(ProductResponse.from(savedProduct));
    }

    @Override
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(deleteProductByIdUseCase.execute(id));
    }   
}
