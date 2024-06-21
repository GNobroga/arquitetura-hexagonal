package br.com.gabriel.hexagonal.domain.outbound;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import br.com.gabriel.hexagonal.domain.model.SearchCriteria;

public interface ProductServicePort {
    Product create(final Product anProduct);
    boolean deleteById(final Long anId);
    Pagination<Product> findAll(SearchCriteria criteria);
}
