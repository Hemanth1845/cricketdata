package com.klef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.dev.entity.Cricketer;

@Repository
public interface CricketerRepository extends JpaRepository<Cricketer, Integer> {
    
}