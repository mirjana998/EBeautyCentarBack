package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.ZaposleniSalonUsluga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZaposleniSalonUslugaRepository extends JpaRepository<ZaposleniSalonUsluga, Long> {

    Optional<ZaposleniSalonUsluga> findByZaposleni_IdAndSalonUsluga_Id(Long zaposleniId, Long salonUslugaId);

}