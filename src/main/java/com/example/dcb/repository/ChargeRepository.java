package com.example.dcb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dcb.entity.Charge;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {

}
