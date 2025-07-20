package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Usluga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UslugaRepository extends JpaRepository<Usluga, Long> {

    List<Usluga> findByStatus(String status);
    @Query("SELECT DISTINCT u.naziv FROM Usluga u")
    List<String> findDistinctNaziv();
}