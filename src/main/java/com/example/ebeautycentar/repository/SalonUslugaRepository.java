package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.SalonUsluga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonUslugaRepository extends JpaRepository<SalonUsluga, Long> {

    List<SalonUsluga> findByStatus(String status);
}