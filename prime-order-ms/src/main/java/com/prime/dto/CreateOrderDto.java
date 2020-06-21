package com.prime.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    @NotEmpty(message = "Link can't be empty")
    private String link;
    @NotEmpty(message = "Currency type can't be empty")
    private String currencyType;
    @NotNull(message = "Price can't be empty")
    private Double price;
    @NotNull(message = "Cargo price can't be empty")
    private Double cargoPrice;
    @NotNull(message = "Count can't be empty")
    private Integer count;
    @NotEmpty(message = "Description can't be empty")
    private String descriptionUser;
}
