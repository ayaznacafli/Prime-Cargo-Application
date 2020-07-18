package com.prime.service;

import com.prime.dto.CreateOrderDto;
import com.prime.dto.OrderListOperatorDTO;
import com.prime.dto.OrderListUserDto;

import java.util.List;

public interface OrderService {

    long createOrder(long userId, CreateOrderDto orderDto);

    void updateOrder(long orderId, CreateOrderDto orderDto);

    void deleteOrder(long orderId);

    List<OrderListUserDto> getOrderListWithUserId(long userId);

    void chooseOrder(long orderId, long operatorId);

    List<OrderListOperatorDTO> getOrderListForOperator();

}
