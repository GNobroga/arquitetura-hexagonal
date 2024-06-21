package br.com.gabriel.hexagonal.adapter.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.gabriel.hexagonal.adapter.inbound.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    boolean existsById(Long id);
}
