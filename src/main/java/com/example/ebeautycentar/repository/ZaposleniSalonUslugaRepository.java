package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.ZaposleniSalonUsluga;
import com.example.ebeautycentar.entity.ZaposleniSalonUslugaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZaposleniSalonUslugaRepository extends JpaRepository<ZaposleniSalonUsluga, ZaposleniSalonUslugaId> {

    Optional<ZaposleniSalonUsluga> findZaposleniSalonUslugaByZaposleniIdAndSalonUslugaId(Long zaposleniId, Long salonUslugaId);
}