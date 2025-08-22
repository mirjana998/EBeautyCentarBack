package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Slika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SlikaRepository extends JpaRepository<Slika, Long> {

    List<Slika> findByStatus(String status);

    List<Slika> findBySalonIdAndStatusAndVrsta(Long salonId, String status, String vrsta);

    Optional<Slika> findByUslugaId(Long uslugaId);
}