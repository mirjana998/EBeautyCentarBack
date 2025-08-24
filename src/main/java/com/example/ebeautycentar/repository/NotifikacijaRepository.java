package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Notifikacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotifikacijaRepository extends JpaRepository<Notifikacija, Long> {

    List<Notifikacija> findByStatus(String status);

    List<Notifikacija> findByKorisnikIdOrderByVrijemeKreiranjaDesc(Long korisnikId);
}