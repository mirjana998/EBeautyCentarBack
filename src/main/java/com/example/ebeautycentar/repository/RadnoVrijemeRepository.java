package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.RadnoVrijeme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RadnoVrijemeRepository extends JpaRepository<RadnoVrijeme, Long> {

    RadnoVrijeme findRadnoVrijemeByDanUSedmiciIdAndSalonId(Integer danUSedmici, Long salon);

    List<RadnoVrijeme> findBySalonId(Long salonId);
}