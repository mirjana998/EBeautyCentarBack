package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.OcjenaKlijenta;
import com.example.ebeautycentar.entity.OcjenaKlijentaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OcjenaKlijentaRepository extends JpaRepository<OcjenaKlijenta, OcjenaKlijentaId> {

    Optional<OcjenaKlijenta> findByVlasnikSalonaIdAndRegistrovaniKlijentId(Long vlasnikSalonaId, Long registrovaniKlijentId);

}