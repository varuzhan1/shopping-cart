package com.shoppingCart.mapper;

import com.shoppingCart.persistence.dto.ProductDto;
import com.shoppingCart.persistence.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper  {


    Product mapToEntity(ProductDto productDto);

    ProductDto mapToDto(Product product);

//    @Override
//    protected void configure(MapperFactory factory) {
//        factory.classMap(Product.class, ProductDto.class)
//                .field("id", "id")
//                .field("name", "name")
//                .field("description", "description")
//                .field("type", "type")
//                .field("price", "price")
//                .field("stockCount", "stockCount")
//                .field("createdDate", "createdDate")
//                .field("updatedDate", "updatedDate")
//                .byDefault()
//                .register();
//    }

}
