package com.prime.carriage.repository;

import com.prime.carriage.model.CarriageDateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarriageDateInfoRepository extends JpaRepository<CarriageDateInfo,Long> {
}
