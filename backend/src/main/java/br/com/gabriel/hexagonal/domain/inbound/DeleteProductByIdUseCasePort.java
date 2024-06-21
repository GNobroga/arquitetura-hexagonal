package br.com.gabriel.hexagonal.domain.inbound;

public interface DeleteProductByIdUseCasePort {
    boolean execute(final Long id);
}
