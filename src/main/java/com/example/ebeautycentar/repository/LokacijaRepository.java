package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Lokacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
    @Query("SELECT DISTINCT l.grad FROM Lokacija l")
    List<String> findDistinctGrad();
    @Query("SELECT DISTINCT l.drzava FROM Lokacija l")
    List<String> findDistinctDrzava();
}