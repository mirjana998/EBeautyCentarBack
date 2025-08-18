package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Long> {

    List<Rezervacija> findByStatus(String status);

    @Query(value = """
    SELECT r.* FROM rezervacija r
    JOIN zaposleni_salon_usluga zsu 
        ON r.zaposleni_salon_usluga_id = zsu.id
    JOIN salon_usluga su 
        ON zsu.salon_usluga_id = su.id
    WHERE su.salon_id = :salonId
    """, nativeQuery = true)
    List<Rezervacija> findBySalonId(@Param("salonId") Long salonId);

    @Query(value= """
        select rez.*
        from rezervacija rez
        join zaposleni_salon_usluga zsu 
            on rez.zaposleni_salon_usluga_id = zsu.id
        join salon_usluga su 
            on zsu.salon_usluga_id = su.id
        where zsu.zaposleni_id= :zaposleniId
        and rez.status = :status
    """, nativeQuery = true)
    List<Rezervacija> findByZaposleniId(@Param("zaposleniId") Long zaposleniId, @Param("status") String status);

    @Query(value= """
        select rez.*
        from rezervacija rez
        where rez.registrovani_klijent_id = :klijentId
    """, nativeQuery = true)
    List<Rezervacija> findByRegistrovaniKlijentId(@Param("klijentId") Long klijentId);

}