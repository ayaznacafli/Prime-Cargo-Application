package com.prime.order.service.impl;

import com.prime.order.exception.OrderDateInfoNotFoundException;
import com.prime.order.model.OrderDateInfo;
import com.prime.order.repository.OrderDateInfoRepository;
import com.prime.order.service.OrderDateInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDateInfoServiceImpl implements OrderDateInfoService {

    private final OrderDateInfoRepository dateInfoRepository;

    @Override
    public OrderDateInfo getOrderDateInfoById(long id) {
        return dateInfoRepository.findById(id).orElseThrow(() ->
                new OrderDateInfoNotFoundException(id));
    }

    @Override
    public void updateDateInfo(OrderDateInfo info) {
        getOrderDateInfoById(info.getId());
        dateInfoRepository.save(info);
    }

    @Override
    public void deleteDateInfo(long id) {
        getOrderDateInfoById(id);
        dateInfoRepository.deleteById(id);
    }
}
