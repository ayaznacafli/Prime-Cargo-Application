package com.prime.order.service;

import com.prime.order.dto.CreateOrderDto;
import com.prime.order.dto.OrderListOperatorDTO;
import com.prime.order.dto.OrderListUserDto;

import java.util.List;

public interface OrderService {

    long createOrder(long userId, CreateOrderDto orderDto);

    void updateOrder(long orderId, CreateOrderDto orderDto);

    void deleteOrder(long orderId);

    List<OrderListUserDto> getOrderListWithUserId(long userId);

    void chooseOrder(long orderId, long operatorId);

    List<OrderListOperatorDTO> getOrderListForOperator();

}
