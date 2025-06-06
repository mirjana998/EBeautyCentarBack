package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    List<Rezervacija> findByStatus(String status);
}