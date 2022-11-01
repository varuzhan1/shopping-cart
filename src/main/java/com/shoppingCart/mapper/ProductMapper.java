package com.shoppingCart.mapper;

import com.shoppingCart.persistence.dto.ProductCreateDto;
import com.shoppingCart.persistence.dto.ProductOutDto;
import com.shoppingCart.persistence.dto.ProductUpdateDto;
import com.shoppingCart.persistence.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

//    public ProductCreateDto convertEntityToCreateDto(Product entity) {
//        return ProductCreateDto.builder()
//                .name(entity.getName())
//                .price(entity.getPrice())
//                .stockCount(entity.getStockCount())
//                .description(entity.getDescription())
//                .type(entity.getType())
//                .build();
//    }

    public Product convertDtoToEntity(ProductCreateDto dto) {

        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        entity.setStockCount(dto.getStockCount());
        return entity;
    }

    public Product convertUpdateDtoToEntity(ProductUpdateDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        entity.setStockCount(dto.getStockCount());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    public ProductOutDto convertEntityToOutDto(Product entity) {
        return ProductOutDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stockCount(entity.getStockCount())
                .description(entity.getDescription())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .type(entity.getType())
                .build();
    }

}
