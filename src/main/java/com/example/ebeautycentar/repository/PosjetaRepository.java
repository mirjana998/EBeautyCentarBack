package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Posjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PosjetaRepository extends JpaRepository<Posjeta, Long> {

    @Query("SELECT p FROM Posjeta p WHERE p.salon.id = :salonId AND FUNCTION('DATE', p.vrijeme) = :dan")
    List<Posjeta> findBySalonAndDate(@Param("salonId") Long salonId, @Param("dan") LocalDate dan);

    @Query("SELECT COUNT(p) FROM Posjeta p WHERE p.salon.id = :salonId AND FUNCTION('DATE', p.vrijeme) = :dan")
    int countBySalonAndDate(@Param("salonId") Long salonId, @Param("dan") LocalDate dan);
    List<Posjeta> findBySalonId(Long salonId);

}
