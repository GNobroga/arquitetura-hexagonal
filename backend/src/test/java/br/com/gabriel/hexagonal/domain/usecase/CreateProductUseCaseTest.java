package br.com.gabriel.hexagonal.domain.usecase;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gabriel.hexagonal.domain.entities.product.Product;
import br.com.gabriel.hexagonal.domain.entities.validation.DomainInvalidException;
import br.com.gabriel.hexagonal.domain.outbound.ProductServicePort;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseTest {

    private Product product;

    @InjectMocks
    private CreateProductUseCase createProductUseCase;

    @Mock
    private ProductServicePort productServicePort;
    
    @BeforeEach
    void setup() {
        product = new Product(1L, "Smartphone", "No description", true, new BigDecimal(10000), "dsdsd");
    }

    @Test
    void givenAValidProduct_whenCallsExecute_thenShouldReturnProduct() {
        final var expectedId = 1L;
        final var expectedName = "Smartphone";
        final var expectedDescription = "No description";
        final var expectedAvailable = true;

        when(productServicePort.create(any())).thenAnswer(returnsFirstArg());

        final var aProduct = createProductUseCase.execute(product);

        Assertions.assertEquals(expectedId, aProduct.getId());
        Assertions.assertEquals(expectedName, aProduct.getName());
        Assertions.assertEquals(expectedDescription, aProduct.getDescription());
        Assertions.assertEquals(expectedAvailable, aProduct.isAvailable());
    }

    @Test
    void givenAInvalidProductName_whenCallsExecute_thenShouldThrowAnException() {
        final var expectedName = "";
        final var expectedErrorMessage = "O nome do produto é obrigatório.";
        final var clone = product.clone();
        clone.setName(expectedName);
        final var aException = Assertions.assertThrows(DomainInvalidException.class, () -> createProductUseCase.execute(clone));
        Assertions.assertEquals(expectedErrorMessage, aException.getErrors().get(0).message());
    }

    @Test
    void givenAInvalidProductNameLength_whenCallsExecute_thenShouldThrowAnException() {
        final var expectedName = "12";
        final var expectedErrorMessage = "O nome precisa ter mais de 3 caracteres.";
        final var clone = product.clone();
        clone.setName(expectedName);
        final var aException = Assertions.assertThrows(DomainInvalidException.class, () -> createProductUseCase.execute(clone));
        Assertions.assertEquals(expectedErrorMessage, aException.getErrors().get(0).message());
    }

    @Test
    void givenAInvalidProductNameLenghtGreatherThen100_whenCallsExecute_thenShouldThrowAnException() {
        final var expectedName = "a".repeat(101);
        final var expectedErrorMessage = "O nome não pode ter mais que 100 caracteres.";
        final var clone = product.clone();
        clone.setName(expectedName);
        final var aException = Assertions.assertThrows(DomainInvalidException.class, () -> createProductUseCase.execute(clone));
        Assertions.assertEquals(expectedErrorMessage, aException.getErrors().get(0).message());
    }

    @Test
    void givenAInvalidProductDescription_whenCallsExecute_thenShouldThrowAnException() {
        final var expectedDescription = "";
        final var expectedErrorMessage = "A descrição do produto é obrigatória.";
        final var clone = product.clone();
        clone.setDescription(expectedDescription);
        final var aException = Assertions.assertThrows(DomainInvalidException.class, () -> createProductUseCase.execute(clone));
        Assertions.assertEquals(expectedErrorMessage, aException.getErrors().get(0).message());
    }

    @Test
    void givenAInvalidProductDescriptionLength_whenCallsExecute_thenShouldThrowAnException() {
        final var expectedDescription = "a".repeat(3);
        final var expectedErrorMessage = "A descrição deve ter mais de 10 caracteres.";
        final var clone = product.clone();
        clone.setDescription(expectedDescription);
        final var aException = Assertions.assertThrows(DomainInvalidException.class, () -> createProductUseCase.execute(clone));
        Assertions.assertEquals(expectedErrorMessage, aException.getErrors().get(0).message());
    }

    @Test
    void givenAInvalidProductDescriptionLenghtGreatherThen255_whenCallsExecute_thenShouldThrowAnException() {
        final var expectedDescription = "a".repeat(256);
        final var expectedErrorMessage = "A descrição não pode ter mais que 255 caracteres.";
        final var clone = product.clone();
        clone.setDescription(expectedDescription);
        final var aException = Assertions.assertThrows(DomainInvalidException.class, () -> createProductUseCase.execute(clone));
        Assertions.assertEquals(expectedErrorMessage, aException.getErrors().get(0).message());
    }
}
