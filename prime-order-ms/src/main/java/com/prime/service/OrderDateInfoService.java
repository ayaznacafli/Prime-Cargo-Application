package com.prime.service;

import com.prime.model.OrderDateInfo;
import java.util.Optional;

public interface OrderDateInfoService {
    Optional<OrderDateInfo > getOrderDateInfoByOrderId(long id);
}
