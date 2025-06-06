package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Slika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlikaRepository extends JpaRepository<Slika, Long> {

    List<Slika> findByStatus(String status);
}