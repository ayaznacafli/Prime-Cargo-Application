package com.prime.service.impl;

import com.prime.model.Order;
import com.prime.model.OrderDateInfo;
import com.prime.repository.OrderDateInfoRepository;
import com.prime.service.OrderDateInfoService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDateInfoServiceImpl implements OrderDateInfoService {

    private final OrderDateInfoRepository dateInfoRepository;


    @Override
    public Optional<OrderDateInfo> getOrderDateInfoByOrderId(long id) {
        return dateInfoRepository.findByOrder(Order.builder().id(id).build());
    }
}
