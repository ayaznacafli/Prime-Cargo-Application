package com.prime.repository;

import com.prime.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    Iterable<Order> findAllByUserId(long userId);

    Iterable<Order> findAllByDeletedStatusAndStatus(int deletedStatus, int status);

}
