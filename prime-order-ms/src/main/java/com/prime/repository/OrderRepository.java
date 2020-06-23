package com.prime.repository;

import com.prime.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    Iterable<Order> findAllByUserId(long userId);
    Iterable<Order> findAllByDeletedStatusAndStatus(int deletedStatus, int status);

    @Query(value = "select s from Order s where s.deletedStatus=?1 and s.status=?2")
    List<Order> findAllByDeletedStatusEqualsAndStatusEquals(int deletedStatus, int status);
}
