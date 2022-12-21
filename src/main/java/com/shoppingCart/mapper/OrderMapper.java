package com.shoppingCart.mapper;

import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper
{


    Order mapToEntity(OrderDto orderDto);

    OrderDto mapToDto(Order order);

//    @Override
//    protected void configure(MapperFactory factory) {
//        factory.classMap(Order.class, OrderDto.class)
//                .field("id", "id")
//                .field("productCount", "productCount")
//                .field("status", "status")
//                .field("product", "product")
//                .field("customer", "customer")
//                .byDefault()
//                .register();
//    }

}
