package com.prime.carriage.repository;

import com.prime.carriage.model.Carriage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarriageRepository extends JpaRepository<Carriage,Long> {
}
