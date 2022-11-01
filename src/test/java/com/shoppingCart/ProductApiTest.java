package com.shoppingCart;


import com.fasterxml.jackson.core.type.TypeReference;
import com.shoppingCart.mapper.TokenManager;
import com.shoppingCart.persistence.dto.ProductOutDto;
import com.shoppingCart.persistence.model.Product;
import com.shoppingCart.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
@Sql(value = {"../../insert_user.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"../../delete_user.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductApiTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private TestRequestUtils testRequestUtils;
    @Autowired
    private ProductDtoUtils productDtoUtils;
    @Autowired
    private TokenManager jwtUtil;

    private String token;

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    void before() {
        token = jwtUtil.generateJwtToken(userRepository.findByEmail("testadmin@gmail.com").orElseThrow());
    }

    @Test
    void create_product() throws Exception {

        ResultActions savedResult = testRequestUtils.request(MockMvcRequestBuilders.post("/product")
                        .header("Authorization", "Bearer ".concat(token)),
                productDtoUtils.buildProductCreateDto()).andExpect(MockMvcResultMatchers.status().isOk());

        ProductOutDto createResultDto = testRequestUtils.getBodyContentAsObject(savedResult, ProductOutDto.class);

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

        ResultActions updateResult = testRequestUtils.request(MockMvcRequestBuilders.put("/product")
                        .header("Authorization", "Bearer ".concat(token)),
                productDtoUtils.buildProductUpdateDto(savedProduct.getId())).andExpect(MockMvcResultMatchers.status().isOk());

        ProductOutDto updateResultDto = testRequestUtils.getBodyContentAsObject(updateResult, ProductOutDto.class);

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

        ResultActions loadByIdResult = testRequestUtils.request(MockMvcRequestBuilders.get("/product")
                .header("Authorization", "Bearer ".concat(token))).andExpect(MockMvcResultMatchers.status().isOk());

        List<ProductOutDto> loadByIdResultDto = testRequestUtils.toList(loadByIdResult, new TypeReference<List<ProductOutDto>>() {
        });

        Assertions.assertEquals(loadByIdResultDto.size(), 2);

    }

    @Test
    void delete_by_id() throws Exception {

        Product savedProduct = productDtoUtils.createProduct();

        testRequestUtils.request(MockMvcRequestBuilders.delete("/product/".concat(savedProduct.getId().toString()))
                        .header("Authorization", "Bearer ".concat(token)),
                productDtoUtils.buildProductUpdateDto(savedProduct.getId())).andExpect(MockMvcResultMatchers.status().isOk());

    }

}
