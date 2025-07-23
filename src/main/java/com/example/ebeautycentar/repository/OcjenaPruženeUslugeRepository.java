package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OcjenaPruženeUslugeRepository extends JpaRepository<OcjenaPruženeUsluge, Long> {

    @Query(value = """
     select opu.*
    from ocjena_pružene_usluge opu
    join rezervacija rez on opu.rezervacija_id = rez.id
    join salon_usluga su on rez.salon_usluga_id = su.id
    join salon s on su.salon_id = s.id
    where s.id = :salon_id;
    """, nativeQuery = true)
    List<OcjenaPruženeUsluge> findAllBySalonId(@Param("salon_id") Long salonId);
}