package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.OcjenaKlijenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OcjenaKlijentaRepository extends JpaRepository<OcjenaKlijenta, Long> {

    Optional<OcjenaKlijenta> findByVlasnikSalonaIdAndRegistrovaniKlijentId(Long vlasnikSalonaId, Long registrovaniKlijentId);

}