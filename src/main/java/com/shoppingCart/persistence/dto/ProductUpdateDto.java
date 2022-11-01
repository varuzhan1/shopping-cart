package com.shoppingCart.persistence.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import java.time.LocalDate;

@Getter
@SuperBuilder
@Jacksonized
@ToString
@Valid
public class ProductUpdateDto extends ProductCreateDto {

    @NonNull
    private Integer id;

    private LocalDate createdDate;

}
