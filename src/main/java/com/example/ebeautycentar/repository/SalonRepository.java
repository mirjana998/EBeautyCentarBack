package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonRepository extends JpaRepository<Salon, Long> {

    List<Salon> findByStatus(String status);

}