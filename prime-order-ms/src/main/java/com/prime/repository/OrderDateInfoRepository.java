package com.prime.repository;

import com.prime.model.Order;
import com.prime.model.OrderDateInfo;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDateInfoRepository extends CrudRepository<OrderDateInfo,Long> {
    Optional<OrderDateInfo> findByOrder(Order order);
}
