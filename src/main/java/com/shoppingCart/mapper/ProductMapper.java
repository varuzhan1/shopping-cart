package com.shoppingCart.mapper;

import com.shoppingCart.persistence.dto.ProductDto;
import com.shoppingCart.persistence.entity.Product;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Product.class, ProductDto.class)
                .field("id", "id")
                .field("name", "name")
                .field("description", "description")
                .field("type", "type")
                .field("price", "price")
                .field("stockCount", "stockCount")
                .field("createdDate", "createdDate")
                .field("updatedDate", "updatedDate")
                .byDefault()
                .register();
    }

}
