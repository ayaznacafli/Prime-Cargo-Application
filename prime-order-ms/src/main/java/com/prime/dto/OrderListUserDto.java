package com.prime.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderListUserDto {
    private long orderId;
    private String link;
    private int count;
    private LocalDateTime createDate;
    private double totalPrice;
    private double carriageOrder;
    private String status;
}
