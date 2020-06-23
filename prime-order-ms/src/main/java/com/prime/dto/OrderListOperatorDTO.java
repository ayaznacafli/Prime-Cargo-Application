package com.prime.dto;

import com.prime.model.CurrencyType;
import lombok.*;

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
