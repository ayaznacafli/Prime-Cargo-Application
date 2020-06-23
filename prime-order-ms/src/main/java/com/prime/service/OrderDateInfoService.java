package com.prime.service;

import com.prime.model.OrderDateInfo;
import java.util.Optional;

public interface OrderDateInfoService {
    OrderDateInfo getOrderDateInfoById(long id);
    void updateDateInfo(OrderDateInfo info);
    void deleteDateInfo(long id);
}
