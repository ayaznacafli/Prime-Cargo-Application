package com.prime.carriage.repository;

import com.prime.carriage.model.CarriageQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarriageQuantityRepository extends JpaRepository<CarriageQuantity,Long> {
}
