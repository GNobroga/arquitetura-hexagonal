package br.com.gabriel.hexagonal.domain.inbound;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import br.com.gabriel.hexagonal.domain.model.SearchCriteria;

public interface FindAllProductUseCasePort {
    Pagination<Product> execute(final SearchCriteria criteria);
}
