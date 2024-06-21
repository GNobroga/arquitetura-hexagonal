package br.com.gabriel.hexagonal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.gabriel.hexagonal.domain.inbound.CreateProductUseCasePort;
import br.com.gabriel.hexagonal.domain.inbound.DeleteProductByIdUseCasePort;
import br.com.gabriel.hexagonal.domain.inbound.FindAllProductUseCasePort;
import br.com.gabriel.hexagonal.domain.outbound.ProductServicePort;
import br.com.gabriel.hexagonal.domain.usecase.CreateProductUseCase;
import br.com.gabriel.hexagonal.domain.usecase.DeleteProductByIdUseCase;
import br.com.gabriel.hexagonal.domain.usecase.FindAllProductUseCase;

@Configuration
public class IoCConfig {
    
    @Bean
    CreateProductUseCasePort createProductUseCasePort(ProductServicePort service) {
        return new CreateProductUseCase(service);
    }

    @Bean
    DeleteProductByIdUseCasePort deleteProductByIdUseCasePort(ProductServicePort service) {
        return new DeleteProductByIdUseCase(service);
    }

    @Bean
    FindAllProductUseCasePort findAllProductUseCasePort(ProductServicePort service) {
        return new FindAllProductUseCase(service);
    }
}
