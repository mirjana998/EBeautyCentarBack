package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipRepository extends JpaRepository<Tip, Integer> {

    List<Tip> findByStatus(String status);
}