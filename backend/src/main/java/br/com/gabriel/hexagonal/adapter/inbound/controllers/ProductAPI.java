package br.com.gabriel.hexagonal.adapter.inbound.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.gabriel.hexagonal.adapter.inbound.controllers.request.CreateProductRequest;
import br.com.gabriel.hexagonal.adapter.inbound.controllers.response.ProductResponse;
import br.com.gabriel.hexagonal.domain.model.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ProductAPI {
    
    @GetMapping
    @Operation(summary = "Lista todos os produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Um erro interno ocorreu"),
    })
    Pagination<ProductResponse> list(
        @RequestParam(name = "term", required = false, defaultValue = "")
        final String term, 
        @RequestParam(name = "page", required = false, defaultValue = "0")
        final int page,
        @RequestParam(name = "size", required = false, defaultValue = "10")
        final int size,
        @RequestParam(name = "sort", required = false, defaultValue = "ASC")
        final String sort);


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Cria um novo membro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "500", description = "Erro interno"),
    })
    ResponseEntity<ProductResponse> create(@RequestBody CreateProductRequest input);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deletar um produto")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno"),
    })
    ResponseEntity<Boolean> deleteById(@PathVariable Long id);
}
