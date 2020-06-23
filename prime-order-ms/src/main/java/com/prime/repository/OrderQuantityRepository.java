package com.prime.repository;

import com.prime.model.OrderQuantity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderQuantityRepository extends CrudRepository<OrderQuantity,Long> {

}
