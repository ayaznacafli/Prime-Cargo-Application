package com.prime.order.service.impl;

import com.prime.order.exception.OrderQuantityNotFoundException;
import com.prime.order.model.OrderQuantity;
import com.prime.order.repository.OrderQuantityRepository;
import com.prime.order.service.OrderQuantityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQuantityServiceImpl implements OrderQuantityService {

    private final OrderQuantityRepository quantityRepository;

    @Override
    public OrderQuantity getOrderQuantityById(long quantityId) {
        return quantityRepository.findById(quantityId).orElseThrow(() ->
                new OrderQuantityNotFoundException(quantityId));
    }
}
