package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Novost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NovostRepository extends JpaRepository<Novost, Long> {

    List<Novost> findByStatus(String status);
}