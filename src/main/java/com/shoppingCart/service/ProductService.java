package com.shoppingCart.service;

import com.shoppingCart.mapper.ProductMapper;
import com.shoppingCart.persistence.dto.ProductCreateDto;
import com.shoppingCart.persistence.dto.ProductOutDto;
import com.shoppingCart.persistence.dto.ProductUpdateDto;
import com.shoppingCart.persistence.model.Product;
import com.shoppingCart.persistence.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private EntityManager entityManager;

    public ProductOutDto create(@Valid ProductCreateDto dto) {

        Product mappedProduct = productMapper.convertDtoToEntity(dto);
        mappedProduct.setCreatedDate(LocalDate.now());
        mappedProduct.setUpdatedDate(LocalDate.now());

        return productMapper.convertEntityToOutDto(productRepository.saveAndFlush(mappedProduct));
    }

    public ProductOutDto update(@Valid ProductUpdateDto dto) {

        Product mappedProduct = productMapper.convertUpdateDtoToEntity(dto);
        mappedProduct.setUpdatedDate(LocalDate.now());

        return productMapper.convertEntityToOutDto(productRepository.saveAndFlush(mappedProduct));
    }

    public List<ProductOutDto> loadAllProducts() {

        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));

        return products.stream().map(productMapper::convertEntityToOutDto).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);

    }

    private Specification<Product> getFilterQuery(String key) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.or(
                    criteriaBuilder.like(root.get("description"), key),
                    criteriaBuilder.like(root.get("name"), key),
                    criteriaBuilder.equal(root.get("type"), key));
        };
    }

    public List<ProductOutDto> loadByKey(String key) {
        List<Product> products = productRepository.findAll(getFilterQuery(key));

        return products.stream().map(productMapper::convertEntityToOutDto).collect(Collectors.toList());
    }
}
