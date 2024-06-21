package br.com.gabriel.hexagonal.domain.usecase;

import br.com.gabriel.hexagonal.domain.inbound.DeleteProductByIdUseCasePort;
import br.com.gabriel.hexagonal.domain.outbound.ProductServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductByIdUseCase implements DeleteProductByIdUseCasePort {

    private final ProductServicePort service;

    @Override
    public boolean execute(Long id) {
        return service.deleteById(id);
    }
    
}
