package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.entity.SalonUsluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonUslugaRepository extends JpaRepository<SalonUsluga, Long> {

    List<SalonUsluga> findByStatus(String status);

    @Query(value = """
     select * from salon_usluga s
     where s.salon_id= :id
     and s.status =:status
    """, nativeQuery = true)
    List<SalonUsluga> findBySalonIdAndStatus(@Param("id") Long salonId, @Param("status") String status);

    @Query(value = """
     select * from salon_usluga s
     where s.salon_id= :id
    """, nativeQuery = true)
    List<SalonUsluga> findBySalonId(@Param("id") Long salonId);


}