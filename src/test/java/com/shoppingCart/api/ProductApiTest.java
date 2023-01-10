package com.shoppingCart.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.shoppingCart.ProductDtoUtils;
import com.shoppingCart.TestRequestUtils;
import com.shoppingCart.persistence.dto.ProductDto;
import com.shoppingCart.persistence.entity.Product;
import com.shoppingCart.persistence.repository.ProductRepository;
import com.shoppingCart.persistence.repository.UserRepository;
import com.shoppingCart.util.TokenManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class ProductApiTest {


    protected MockMvc mockMvc;

    private TestRequestUtils testRequestUtils;

    private ProductDtoUtils productDtoUtils;

    private TokenManager jwtUtil;

    private String token;

    UserRepository userRepository;

    ProductRepository productRepository;

    @Autowired
    public ProductApiTest(MockMvc mockMvc, TestRequestUtils testRequestUtils, ProductDtoUtils productDtoUtils,
                          TokenManager jwtUtil, UserRepository userRepository, ProductRepository productRepository) {
        this.mockMvc = mockMvc;
        this.testRequestUtils = testRequestUtils;
        this.productDtoUtils = productDtoUtils;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @BeforeEach
    void before() {
        token = jwtUtil.generateJwtToken(userRepository.findByEmail("testadmin@gmail.com").orElseThrow());
    }

    @AfterEach
    void after() {
        productRepository.deleteAll();
    }

    @Test
    void create_product() throws Exception {

        ResultActions savedResult = testRequestUtils.request(MockMvcRequestBuilders.post("/products")
                        .header("Authorization", "Bearer ".concat(token)),
                productDtoUtils.buildProductCreateDto()).andExpect(MockMvcResultMatchers.status().isOk());

        ProductDto createResultDto = testRequestUtils.getBodyContentAsObject(savedResult, ProductDto.class);

        Assertions.assertNotNull(createResultDto);
        Assertions.assertNotNull(createResultDto.getId());
        Assertions.assertNotNull(createResultDto.getDescription());
        Assertions.assertNotNull(createResultDto.getName());
        Assertions.assertNotNull(createResultDto.getStockCount());
        Assertions.assertNotNull(createResultDto.getUpdatedDate());
        Assertions.assertNotNull(createResultDto.getCreatedDate());
        Assertions.assertNotNull(createResultDto.getType());

    }

    @Test
    void update_product() throws Exception {

        Product savedProduct = productDtoUtils.createProduct();

        ResultActions updateResult = testRequestUtils.request(MockMvcRequestBuilders.put("/products")
                        .header("Authorization", "Bearer ".concat(token)),
                productDtoUtils.buildProductUpdateDto(savedProduct.getId())).andExpect(MockMvcResultMatchers.status().isOk());

        ProductDto updateResultDto = testRequestUtils.getBodyContentAsObject(updateResult, ProductDto.class);

        Assertions.assertEquals(savedProduct.getId(), updateResultDto.getId());
        Assertions.assertEquals(savedProduct.getCreatedDate(), updateResultDto.getCreatedDate());
        Assertions.assertEquals(savedProduct.getUpdatedDate(), updateResultDto.getUpdatedDate());
        Assertions.assertNotEquals(savedProduct.getName(), updateResultDto.getName());
        Assertions.assertNotEquals(savedProduct.getPrice(), updateResultDto.getPrice());
        Assertions.assertNotEquals(savedProduct.getDescription(), updateResultDto.getDescription());
        Assertions.assertNotEquals(savedProduct.getType(), updateResultDto.getType());

    }

    @Test
    void load_products() throws Exception {

        productDtoUtils.createProduct();
        productDtoUtils.createProduct();

        ResultActions loadByIdResult = testRequestUtils.request(MockMvcRequestBuilders.get("/products")
                .header("Authorization", "Bearer ".concat(token))).andExpect(MockMvcResultMatchers.status().isOk());

        List<ProductDto> loadByIdResultDto = testRequestUtils.toList(loadByIdResult, new TypeReference<List<ProductDto>>() {
        });

        Assertions.assertEquals(loadByIdResultDto.size(), 2);

    }

    @Test
    void delete_by_id() throws Exception {

        Product savedProduct = productDtoUtils.createProduct();

        testRequestUtils.request(MockMvcRequestBuilders.delete("/products/".concat(savedProduct.getId().toString()))
                        .header("Authorization", "Bearer ".concat(token)),
                productDtoUtils.buildProductUpdateDto(savedProduct.getId())).andExpect(MockMvcResultMatchers.status().isOk());

    }

}
