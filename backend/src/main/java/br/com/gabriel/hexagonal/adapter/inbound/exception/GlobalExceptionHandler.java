package br.com.gabriel.hexagonal.adapter.inbound.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gabriel.hexagonal.domain.entities.validation.DomainInvalidException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;
    
    @ExceptionHandler(DomainInvalidException.class)
    public ResponseEntity<?> handleDomainInvalidException(final DomainInvalidException ex) {
        return buildResponseError(ex.getErrors().stream().map(error -> error.message()).toList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex) {
        return buildResponseError(ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
    }

    public ResponseEntity<?> buildResponseError(List<String> errors) {
        final var objectResponse = objectMapper.createObjectNode();
        objectResponse.put("title", "Ocorreu um erro");
        try {
            objectResponse.put("errors", objectMapper.writeValueAsString(errors));
        } catch (Throwable error) {}
        return ResponseEntity.badRequest().body(objectResponse);
    }
}
