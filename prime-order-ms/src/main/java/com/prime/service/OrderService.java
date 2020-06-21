package com.prime.service;

import com.prime.dto.CreateOrderDto;
import com.prime.dto.OrderFilterDto;
import com.prime.dto.OrderListUserDto;
import com.prime.model.Order;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    long createOrder(long userId, CreateOrderDto orderDto);

    void updateOrder(long orderId, CreateOrderDto orderDto);

    void deleteOrder(long orderId);

    List<OrderListUserDto> getOrderListWithUserId(long userId);

    void chooseOrder(long orderId, long operatorId);

    Iterable<Order> getOrderListForOperator(OrderFilterDto orderFilterDto, Pageable pageable);

}
