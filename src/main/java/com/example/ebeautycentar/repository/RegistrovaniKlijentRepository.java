package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.RegistrovaniKlijent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrovaniKlijentRepository extends JpaRepository<RegistrovaniKlijent, Long> {
    Optional<RegistrovaniKlijent> findByKorisnikId(Long korisnikId);
}