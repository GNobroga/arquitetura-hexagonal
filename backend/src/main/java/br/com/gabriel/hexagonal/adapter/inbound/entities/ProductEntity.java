package br.com.gabriel.hexagonal.adapter.inbound.entities;

import java.math.BigDecimal;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "products")
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ProductEntity extends BaseEntity {
    
    private String name;

    private String description;

    private boolean available;

    private BigDecimal price;

    private String imageUrl;

    public static ProductEntity from(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .available(product.isAvailable())
            .price(product.getPrice())
            .imageUrl(product.getImageUrl())
            .build();
    }

    public static Product toProduct(ProductEntity productEntity) {
        return new Product(
            productEntity.getId(), 
            productEntity.getName(), 
            productEntity.getDescription(), 
            productEntity.isAvailable(),
            productEntity.getPrice(),
            productEntity.getImageUrl());
    }
}
