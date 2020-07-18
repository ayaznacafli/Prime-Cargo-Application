package com.prime.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderListOperatorDTO {

    private Long id;

    private String link;
    private String currency;
    private double price;
    private double cargoPrice;
    private int count;
    private double totalPrice;
    private double carriageOrder;

    private String descriptionUser;
    private String descriptionOperator;

    private String status;

    private long userId;
}
