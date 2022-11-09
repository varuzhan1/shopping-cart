package com.shoppingCart;

import com.shoppingCart.persistence.dto.ProductDto;
import com.shoppingCart.persistence.entity.Product;
import com.shoppingCart.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductDtoUtils {

    @Autowired
    private ProductRepository productRepository;

    public ProductDto buildProductCreateDto() {
        return ProductDto.builder()
                .name("Product create name")
                .description("Product crate description")
                .price(50)
                .stockCount(50)
                .type("shor")
                .build();
    }

    public ProductDto buildProductUpdateDto(Integer id) {
        return ProductDto.builder()
                .id(id)
                .name("Product update name")
                .description("Product update description")
                .price(50)
                .stockCount(50)
                .type("shor")
                .createdDate(LocalDate.now())
                .build();
    }

    public Product createProduct() {
        Product product = new Product();
        product.setName("Product name");
        product.setDescription("Product desc");
        product.setType("Product type");
        product.setPrice(500);
        product.setStockCount(15);
        product.setCreatedDate(LocalDate.now());
        product.setUpdatedDate(LocalDate.now());
        return productRepository.saveAndFlush(product);
    }

}
