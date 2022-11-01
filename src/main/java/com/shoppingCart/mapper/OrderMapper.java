package com.shoppingCart.mapper;

import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.model.Order;
import com.shoppingCart.persistence.model.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto convertEntityToDto(Order entity) {

        return OrderDto.builder()
                .id(entity.getId())
                .productCount(entity.getProductCount())
                .product(entity.getProduct())
                .customer(entity.getCustomer())
                .status(entity.getStatus().toString())
                .build();
    }

    public Order convertDtoToEntity(OrderDto dto) {

        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setCustomer(dto.getCustomer());
        entity.setProduct(dto.getProduct());
        entity.setProductCount(dto.getProductCount());
        entity.setStatus(OrderStatus.valueOf(dto.getStatus()));
        return entity;
    }

}
