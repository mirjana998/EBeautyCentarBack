package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    List<Korisnik> findByStatus(String status);
    Optional<Korisnik> findByEmail(String email);
    Optional<Korisnik> findByClerkUserId(String clerkUserId);
}