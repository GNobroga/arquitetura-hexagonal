package br.com.gabriel.hexagonal.adapter.outbound;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gabriel.hexagonal.adapter.inbound.entities.ProductEntity;
import br.com.gabriel.hexagonal.domain.entities.product.Product;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import br.com.gabriel.hexagonal.domain.model.SearchCriteria;
import br.com.gabriel.hexagonal.domain.outbound.ProductServicePort;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceAdapter implements ProductServicePort {

    private final ProductRepository repository;

    @Override
    public Product create(Product anProduct) {
        final var entity = ProductEntity.from(anProduct);
        repository.save(entity);
        anProduct.setId(entity.getId());
        return anProduct;
    }

    @Override
    public boolean deleteById(Long anId) {
        if (repository.existsById(anId)) {
            repository.deleteById(anId);
            return true;
        }
        return false;
    }

    @Override
    public Pagination<Product> findAll(final SearchCriteria criteria) {
        final var direction = "asc".equals(criteria.getSort()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        final var pageRequest = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(direction, "id"));
        final var page = repository.findAll(buildSearchSpec(criteria.getTerm()), pageRequest);
        final var products = page.getContent().stream().map(ProductEntity::toProduct).toList();
        return Pagination.with(page.getSize(),page.getNumber(), page.getTotalElements(), products);
    }

    public Specification<ProductEntity> buildSearchSpec(String term) {
        return (root, query, cb) -> {
            if (Objects.isNull(term) || term.isBlank()) {
                return cb.and();
            }
            return cb.or(
                    cb.like(cb.lower(root.get("name")), cb.lower(cb.literal("%" + term + "%"))),
                    cb.like(cb.lower(root.get("description")), cb.lower(cb.literal("%" + term + "%")))
                );
        };
    }
    
}
