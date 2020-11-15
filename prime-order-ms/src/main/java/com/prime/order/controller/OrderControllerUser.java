package com.prime.order.controller;

import com.prime.order.dto.CreateOrderDto;
import com.prime.order.dto.OrderListUserDto;
import com.prime.order.service.OrderService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class OrderControllerUser {

    private static final String NEGATIVE_ID_MESSAGE = "Id must be positive";

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public List<OrderListUserDto> getOrderList(@Positive(message = NEGATIVE_ID_MESSAGE)
                            @PathVariable(name = "userId") long userId) {
        return orderService.getOrderListWithUserId(userId);
    }

    @PostMapping("/{userId}")
    public long userCreateOrder(@PathVariable(name = "userId")
                                @Positive(message = NEGATIVE_ID_MESSAGE) long userId,
                                @RequestBody @Valid CreateOrderDto order) {
        return orderService.createOrder(userId, order);
    }

    @PutMapping("/{orderId}")
    public void updateCreateOrder(@Positive(message = NEGATIVE_ID_MESSAGE)
                                 @PathVariable(name = "orderId") long orderId,
                                @RequestBody @Valid CreateOrderDto order) {
        orderService.updateOrder(orderId, order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@Positive(message = NEGATIVE_ID_MESSAGE)
                            @PathVariable(name = "orderId") long orderId) {
        orderService.deleteOrder(orderId);
    }

}
