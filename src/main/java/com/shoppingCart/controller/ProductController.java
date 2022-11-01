package com.shoppingCart.controller;

import com.shoppingCart.persistence.dto.ProductCreateDto;
import com.shoppingCart.persistence.dto.ProductOutDto;
import com.shoppingCart.persistence.dto.ProductUpdateDto;
import com.shoppingCart.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<ProductOutDto> getProduct(@RequestParam(required = false) String filter) {
        if (filter == null || filter.isEmpty()) {
            return productService.loadAllProducts();
        } else {
            return productService.loadByKey(filter);
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductOutDto create(@RequestBody ProductCreateDto dto) {
        log.trace("Received to create: {}", dto);
        return productService.create(dto);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductOutDto update(@RequestBody ProductUpdateDto dto) {
        log.trace("Received to update: {}", dto);
        return productService.update(dto);

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable Integer id) {
        log.trace("Received to delete: {}", id);
        productService.delete(id);
    }

}
