package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.DailyStatsDto;
import com.example.ebeautycentar.entity.Posjeta;
import com.example.ebeautycentar.entity.Rezervacija;
import com.example.ebeautycentar.service.RezervacijaService;
import com.example.ebeautycentar.service.PosjetaService; // napravi servis ako ga nema
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analytics")
@CrossOrigin(origins = "http://localhost:3000")
public class AnalyticsController {

    private final RezervacijaService rezervacijaService;
    private final PosjetaService posjetaService;

    public AnalyticsController(RezervacijaService rezervacijaService, PosjetaService posjetaService) {
        this.rezervacijaService = rezervacijaService;
        this.posjetaService = posjetaService;
    }

    @GetMapping("/daily")
    public List<DailyStatsDto> getDailyStats() {
        // 1. Dohvati sve rezervacije
        List<Rezervacija> sveRezervacije = rezervacijaService.getAllRezervacija();

        // 2. Dohvati sve posjete
        List<Posjeta> svePosjete = posjetaService.getAllPosjete(); // napravi entitet Posjeta ako ga nema

        // 3. Grupiraj po danu
        Map<LocalDate, Integer> rezervacijePoDanu = sveRezervacije.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getTerminPocetkaUsluge().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.summingInt(r -> 1)
                ));

        Map<LocalDate, Integer> posjetePoDanu = svePosjete.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getVrijeme().atZone(ZoneId.systemDefault()).toLocalDate(),
                        Collectors.summingInt(p -> 1)
                ));

        // 4. Napravi listu svih dana
        Set<LocalDate> sviDani = new HashSet<>();
        sviDani.addAll(rezervacijePoDanu.keySet());
        sviDani.addAll(posjetePoDanu.keySet());

        List<DailyStatsDto> stats = sviDani.stream()
                .map(dan -> new DailyStatsDto(
                        dan,
                        posjetePoDanu.getOrDefault(dan, 0),
                        rezervacijePoDanu.getOrDefault(dan, 0)
                ))
                .sorted(Comparator.comparing(DailyStatsDto::getDate))
                .collect(Collectors.toList());

        return stats;
    }
}
