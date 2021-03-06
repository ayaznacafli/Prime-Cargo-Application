package com.prime.order.controller;

import com.prime.order.dto.OrderListOperatorDTO;
import com.prime.order.service.OrderService;
import java.util.List;
import javax.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/operator")
public class OrderControllerOperator {

    private static final String NEGATIVE_ID_MESSAGE = "Id must be positive";

    private final OrderService orderService;


    @PutMapping("/{orderId}/{operatorId}")
    public void chooseOrder(@PathVariable(name = "orderId")
                            @Positive(message = NEGATIVE_ID_MESSAGE) long orderId,
                            @PathVariable(name = "operatorId")
                            @Positive(message = NEGATIVE_ID_MESSAGE) long operatorId) {
        orderService.chooseOrder(orderId, operatorId);
    }

    @GetMapping
    public List<OrderListOperatorDTO> getOrderList() {
        return orderService.getOrderListForOperator();
    }


}
