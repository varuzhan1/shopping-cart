package com.shoppingCart;

import com.shoppingCart.persistence.dto.ProductCreateDto;
import com.shoppingCart.persistence.dto.ProductUpdateDto;
import com.shoppingCart.persistence.model.Product;
import com.shoppingCart.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ProductDtoUtils {

    @Autowired
    private ProductRepository productRepository;

    public ProductCreateDto buildProductCreateDto() {
        return ProductCreateDto.builder()
                .name("Product create name")
                .description("Product crate description")
                .price(50)
                .stockCount(50)
                .type("shor")
                .build();
    }

    public ProductUpdateDto buildProductUpdateDto(Integer id) {
        return ProductUpdateDto.builder()
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
