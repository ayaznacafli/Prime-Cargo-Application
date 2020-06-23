package com.prime.service.impl;

import com.prime.exception.OrderDateInfoNotFoundException;
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
