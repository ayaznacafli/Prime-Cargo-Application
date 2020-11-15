package com.prime.order.service;

import com.prime.order.model.OrderDateInfo;

public interface OrderDateInfoService {

    OrderDateInfo getOrderDateInfoById(long id);

    void updateDateInfo(OrderDateInfo info);

    void deleteDateInfo(long id);
}
