package com.shoppingCart.mapper;

import com.shoppingCart.persistence.dto.OrderDto;
import com.shoppingCart.persistence.entity.Order;
import com.shoppingCart.persistence.entity.OrderStatus;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Order.class, OrderDto.class)
                .field("id", "id")
                .field("productCount", "productCount")
                .field("status", "status")
                .field("product", "product")
                .field("customer", "customer")
                .byDefault()
                .register();
    }

}
