package com.prime.dto;

import com.prime.model.StatusType;
import java.util.Date;
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
    private Date createDate;
    private double totalPrice;
    private double carriageOrder;
    private StatusType status;
}
