package com.prime.order.repository;

import com.prime.order.model.Order;
import com.prime.order.model.OrderDateInfo;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDateInfoRepository extends CrudRepository<OrderDateInfo,Long> {
    Optional<OrderDateInfo> findByOrder(Order order);
}
