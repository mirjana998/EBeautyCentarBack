package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

    List<Salon> findByStatus(String status);
    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN zaposleni z ON z.salon_id = s.id
    JOIN zaposleni_salon_usluga zsu ON zsu.zaposleni_id = z.id
    JOIN salon_usluga su ON su.id = zsu.salon_usluga_id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE l.grad = :grad AND u.naziv = :usluga AND s.status= :status
    """, nativeQuery = true)
    List<Salon> findByGradAndUsluga(@Param("grad") String grad, @Param("usluga") String usluga,@Param("status")String status);

    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN zaposleni z ON z.salon_id = s.id
    JOIN zaposleni_salon_usluga zsu ON zsu.zaposleni_id = z.id
    JOIN salon_usluga su ON su.id = zsu.salon_usluga_id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE l.grad = :grad AND s.naziv like :naziv AND s.status= :status
    """, nativeQuery = true)
    List<Salon> findByGradAndNaziv(@Param("grad") String grad, @Param("naziv") String usluga,@Param("status")String status);


    List<Salon> findByNazivLikeAndStatusLike(String naziv, String status);
    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN zaposleni z ON z.salon_id = s.id
    JOIN zaposleni_salon_usluga zsu ON zsu.zaposleni_id = z.id
    JOIN salon_usluga su ON su.id = zsu.salon_usluga_id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE l.grad = :grad AND s.status= :status
    """, nativeQuery = true)
    List<Salon>findByGradAndStatus(@Param("grad") String grad, @Param("status") String status);

    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN zaposleni z ON z.salon_id = s.id
    JOIN zaposleni_salon_usluga zsu ON zsu.zaposleni_id = z.id
    JOIN salon_usluga su ON su.id = zsu.salon_usluga_id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE u.naziv = :usluga AND s.status= :status
    """, nativeQuery = true)
    List<Salon>findByUslugaAndStatus(@Param("usluga") String usluga, @Param("status") String status);

    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN zaposleni z ON z.salon_id = s.id
    JOIN zaposleni_salon_usluga zsu ON zsu.zaposleni_id = z.id
    JOIN salon_usluga su ON su.id = zsu.salon_usluga_id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE u.naziv = :usluga AND s.naziv like :naziv AND s.status= :status
    """, nativeQuery = true)
    List<Salon>findByUslugaAndNaziv(@Param("usluga") String usluga, @Param("naziv") String naziv,@Param("status") String status);

    @Query(value = """
    SELECT DISTINCT s.* 
    FROM salon s
    JOIN zaposleni z ON z.salon_id = s.id
    JOIN zaposleni_salon_usluga zsu ON zsu.zaposleni_id = z.id
    JOIN salon_usluga su ON su.id = zsu.salon_usluga_id
    JOIN usluga u ON u.id = su.usluga_id
    JOIN lokacija l ON s.lokacija_id = l.id
    WHERE l.grad=:grad AND u.naziv = :usluga AND s.naziv like :naziv AND s.status= :status
    """, nativeQuery = true)
    List<Salon>findByGradAndUslugaAndNaziv(@Param("grad") String grad,@Param("usluga") String usluga, @Param("naziv") String naziv,@Param("status") String status);

    @Query(value = """
    select sal.id, sal.naziv, sal.email, sal.broj_telefona, sal.tip_id, sal.datum_otvaranja, sal.status, sal.datum_zatvaranja, sal.vlasnik_salona_id, sal.lokacija_id from
     (select s.*, count(r.id) as c
        from salon s
        join salon_usluga su ON su.salon_id = s.id
        join rezervacija r ON r.salon_usluga_id = su.id
        where r.status = :status_r and s.status= :status_s
        group by 1) as sal
        order by sal.c desc
    """, nativeQuery = true)
    List<Salon>findPopularni(@Param("status_r") String statusR, @Param("status_s") String statusS);

}