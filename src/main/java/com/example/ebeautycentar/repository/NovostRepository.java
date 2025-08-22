package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Novost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NovostRepository extends JpaRepository<Novost, Long> {

    List<Novost> findByStatus(String status);

    @Query(value = """
    select n.* from novost n
   	join salon s on s.id = n.salon_id
       where n.salon_id = :salon_id and n.status = :status
    order by n.vrijeme_kreiranja desc
    """, nativeQuery = true)
    List<Novost> findBySalonId(@Param("salon_id") Long salonId, @Param("status") String status);
}