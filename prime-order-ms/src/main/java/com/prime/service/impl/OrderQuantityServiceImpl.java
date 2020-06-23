package com.prime.service.impl;

import com.prime.exception.OrderQuantityNotFoundException;
import com.prime.model.OrderQuantity;
import com.prime.repository.OrderQuantityRepository;
import com.prime.service.OrderQuantityService;
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
