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
        final var direction = "ASC".equals(criteria.getSort()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        final var pageRequest = PageRequest.of(criteria.getPage(), criteria.getSize(), Sort.by(direction, "id"));
        Specification<ProductEntity> spec = Specification.where(null);

        if (Objects.nonNull(criteria.getTerm()) && !criteria.getTerm().isBlank()) {
            spec = Specification.where((root, query, cb) -> {
                final var predicate = cb.or(
                    cb.like(root.get("name"), cb.literal("%" + criteria.getTerm() + "%")),
                    cb.like(root.get("description"), cb.literal("%" + criteria.getTerm() + "%"))
                );
                return predicate;
            });
        }
        
        final var page = repository.findAll(spec, pageRequest);
        final var products = page.getContent().stream().map(ProductEntity::to).toList();
        return Pagination.with(page.getSize(),page.getNumber(), page.getTotalElements(), products);
    }
    
}
