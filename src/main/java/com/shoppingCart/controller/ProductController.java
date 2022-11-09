package com.shoppingCart.controller;

import com.shoppingCart.persistence.dto.ProductDto;
import com.shoppingCart.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "/products")
@Tag(name = "product", description = "the product API")
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "find products", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @GetMapping()
    public List<ProductDto> getProduct(@RequestParam(required = false) String filter) {
        if (filter == null || filter.isEmpty()) {
            return productService.loadAllProducts();
        } else {
            return productService.loadByKey(filter);
        }
    }

    @Operation(summary = "create product", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDto create(@RequestBody ProductDto dto) {
        log.trace("Received to create: {}", dto);
        return productService.create(dto);
    }

    @Operation(summary = "Update an existing product", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "product not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDto update(@RequestBody ProductDto dto) {
        log.trace("Received to update: {}", dto);
        return productService.update(dto);

    }

    @Operation(summary = "Deletes a product", tags = {"product"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "product not found")})
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable Integer id) {
        log.trace("Received to delete: {}", id);
        productService.delete(id);
    }

}
