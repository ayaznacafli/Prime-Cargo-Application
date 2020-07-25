package com.prime.repository;

import com.prime.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {

    Iterable<Order> findAllByUserId(long userId);

    Iterable<Order> findAllByDeletedStatusAndStatus(int deletedStatus, int status);

    @Query(value = "select s from Order s where s.deletedStatus=:deleteStatus and s.status=:status",nativeQuery = true)
    List<Order> findAllByDeletedStatusEqualsAndStatusEquals(int deletedStatus, int status);


}
