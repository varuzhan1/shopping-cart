package com.shoppingCart.persistence.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@Jacksonized
public class ProductOutDto {

    private Integer id;

    private String name;

    private String description;

    private String type;

    private Integer price;

    private Integer stockCount;

    private LocalDate createdDate;

    private LocalDate updatedDate;

}
