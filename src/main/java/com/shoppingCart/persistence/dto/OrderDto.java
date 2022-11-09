package com.shoppingCart.persistence.dto;

import com.shoppingCart.persistence.entity.OrderUser;
import com.shoppingCart.persistence.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Builder
@Jacksonized
public class OrderDto {

    private Integer id;

    @NonNull
    private Integer productCount;

    @NonNull
    private String status;

    @NonNull
    private Product product;

    @NonNull
    private OrderUser customer;

}
