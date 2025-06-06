package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UslugaRepository extends JpaRepository<Usluga, Long> {

    List<Usluga> findByStatus(String status);
}