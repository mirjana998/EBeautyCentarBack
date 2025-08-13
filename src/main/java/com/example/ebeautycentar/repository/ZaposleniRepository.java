package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Zaposleni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZaposleniRepository extends JpaRepository<Zaposleni, Long> {

    List<Zaposleni> findByAktivan(String aktivan);

    List<Zaposleni> findBySalonId(long salonId);
}