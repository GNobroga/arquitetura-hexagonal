package br.com.gabriel.hexagonal.adapter.inbound.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

}
