package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    List<Korisnik> findByStatus(String status);
}