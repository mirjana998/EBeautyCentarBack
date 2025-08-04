package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.OcjenaPruženeUsluge;
import com.example.ebeautycentar.entity.Transakcija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransakcijaRepository extends JpaRepository<Transakcija, Long> {

  @Query(value = """
    select tr.*
    from transakcija tr
    join rezervacija rez on tr.rezervacija_id = rez.id
    join zaposleni_salon_usluga zsu on rez.zaposleni_salon_usluga_id = zsu.id
    join salon_usluga su on zsu.salon_usluga_id = su.id
    join salon s on su.salon_id = s.id
    where s.id = :salon_id
    and tr.status= :status
    """, nativeQuery = true)
  List<Transakcija> findAllBySalonId(@Param("salon_id") Long salonId, @Param("status") String status);

}