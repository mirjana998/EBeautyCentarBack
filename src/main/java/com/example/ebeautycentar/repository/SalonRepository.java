package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

    List<Salon> findByStatus(String status);

    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN salon_usluga su ON su.salon_id = s.id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE (l.grad like :grad or :grad is null) 
      AND (u.id = :usluga or :usluga is null) 
      AND (s.naziv like :naziv or :naziv is null)
      AND s.status= :status
    """, nativeQuery = true)
    List<Salon>findByGradAndUslugaAndNaziv(@Param("grad") String grad,@Param("usluga") Integer usluga, @Param("naziv") String naziv,@Param("status") String status);

    @Query(value = """
    select sal.id, sal.naziv, sal.email, sal.broj_telefona, sal.tip_id, sal.datum_otvaranja, 
           sal.status, sal.datum_zatvaranja, sal.vlasnik_salona_id, sal.lokacija_id, sal.prosjecna_ocjena
    from (
        select s.*, count(r.id) as c
        from salon s
        join salon_usluga su ON su.salon_id = s.id
        join zaposleni_salon_usluga zsu on su.id = zsu.salon_usluga_id
        left join rezervacija r ON r.zaposleni_salon_usluga_id = zsu.id   
        group by s.id
    ) as sal
    order by sal.c desc
    """, nativeQuery = true)
    List<Salon> findPopularni();


    Optional<Salon> findByNaziv(String naziv);

    List<Salon> findByVlasnikSalonaId(Long vlasnikSalonaId);
}