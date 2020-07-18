package com.prime.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDto {
    @NotEmpty(message = "Link can't be empty.")
    private String link;
    @NotEmpty(message = "Currency type can't be empty.")
    private String currencyType;
    @NotNull(message = "Price can't be empty.")
    @PositiveOrZero(message = "Price most be positive.")
    private Double price;
    @NotNull(message = "Cargo price can't be empty.")
    @Positive(message = "Cargo price most be positive.")
    private Double cargoPrice;
    @NotNull(message = "Count can't be empty.")
    @PositiveOrZero(message = "Count most be positive.")
    private Integer count;
    @NotEmpty(message = "Description can't be empty.")
    private String descriptionUser;
}
