package com.shoppingCart.persistence.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;

@Getter
@SuperBuilder
@Jacksonized
@ToString
@Valid
public class ProductCreateDto {

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String type;

    @NonNull
    private Integer price;

    @NonNull
    private Integer stockCount;

}
