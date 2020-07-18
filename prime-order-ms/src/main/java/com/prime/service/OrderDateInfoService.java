package com.prime.service;

import com.prime.model.OrderDateInfo;

public interface OrderDateInfoService {

    OrderDateInfo getOrderDateInfoById(long id);

    void updateDateInfo(OrderDateInfo info);

    void deleteDateInfo(long id);
}
