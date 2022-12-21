package com.shoppingCart.service;

import com.shoppingCart.mapper.ProductMapper;
import com.shoppingCart.persistence.dto.ProductDto;
import com.shoppingCart.persistence.entity.Product;
import com.shoppingCart.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductDto create(@Valid ProductDto dto) {

        Product mappedProduct = productMapper.mapToEntity(dto);
        mappedProduct.setCreatedDate(LocalDate.now());
        mappedProduct.setUpdatedDate(LocalDate.now());

        return productMapper.mapToDto(productRepository.saveAndFlush(mappedProduct));
    }

    public ProductDto update(@Valid ProductDto dto) {

        Product mappedProduct = productMapper.mapToEntity(dto);
        mappedProduct.setUpdatedDate(LocalDate.now());

        return productMapper.mapToDto(productRepository.saveAndFlush(mappedProduct));
    }

    public List<ProductDto> loadAllProducts() {

        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        return products.stream().map(item -> productMapper.mapToDto(item)).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);

    }

    private Specification<Product> getFilterQuery(String key) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.like(root.get("description"), key),
                criteriaBuilder.like(root.get("name"), key),
                criteriaBuilder.equal(root.get("type"), key));
    }

    public List<ProductDto> loadByKey(String key) {

        List<Product> products = productRepository.findAll(getFilterQuery(key));
        return products.stream().map(item -> productMapper.mapToDto(item)).collect(Collectors.toList());
    }
}
