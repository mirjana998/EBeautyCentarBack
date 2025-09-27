package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.PosjetaDto;
import com.example.ebeautycentar.entity.Posjeta;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.service.PosjetaService;
import com.example.ebeautycentar.service.SalonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posjeta")
@CrossOrigin(origins = "http://localhost:3000")
public class PosjetaController {

    private final PosjetaService posjetaService;
    private final SalonService salonService;

    public PosjetaController(PosjetaService posjetaService, SalonService salonService) {
        this.posjetaService = posjetaService;
        this.salonService = salonService;
    }

    // Loguje posjetu za salon
    @PostMapping("/{salonId}")
    public ResponseEntity<Void> logPosjeta(@PathVariable Long salonId, HttpServletRequest request) {
        Salon salon = salonService.getSalonById(salonId).orElse(null);
        if (salon == null) return ResponseEntity.badRequest().build();

        String ip = request.getRemoteAddr();
        posjetaService.savePosjeta(salon, ip);
        return ResponseEntity.ok().build();
    }

    // Vraca sve posjete za salon
    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<PosjetaDto>> getPosjete(@PathVariable Long salonId) {
        List<PosjetaDto> dtoList = posjetaService.getAllPosjeteForSalon(salonId)
                .stream()
                .map(p -> new PosjetaDto(p.getId(), p.getSalon().getId(), p.getVrijeme()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    //loguje posjetu za cijeli sajt
    @PostMapping("/log")
    public ResponseEntity<Void> logGlobalnuPosjetu(@RequestHeader(value="User-Agent", required=false) String userAgent,
                                                   HttpServletRequest request) {
        String ip = request.getRemoteAddr();

        posjetaService.savePosjeta(new Posjeta(null, Instant.now(), ip, userAgent));

        return ResponseEntity.ok().build();
    }

//vraca sve posjete za cijeli sajt
    @GetMapping("/all")
    public ResponseEntity<List<PosjetaDto>> getAllPosjete() {
        List<PosjetaDto> dtoList = posjetaService.getAllPosjete()
                .stream()
                .map(p -> new PosjetaDto(p.getId(), p.getIpAdresa(), p.getUserAgent(), p.getVrijeme()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
    @GetMapping("/stats/daily")
    public ResponseEntity<List<Map<String, Object>>> getDailyVisits() {
        List<Posjeta> posjete = posjetaService.getAllPosjete();

        Map<LocalDate, Long> dailyCounts = posjete.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getVrijeme().atZone(ZoneId.of("UTC")).toLocalDate(),
                        Collectors.counting()
                ));

        List<Map<String, Object>> result = dailyCounts.entrySet().stream()
                .map(e -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("date", e.getKey().toString());
                    m.put("pageviews", e.getValue());
                    return m;
                })
                .sorted(Comparator.comparing(m -> LocalDate.parse((String)m.get("date"))))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    //vraca posjete u zadnjih 30dana
    @GetMapping("/stats/daily/30days")
    public ResponseEntity<List<Map<String, Object>>> getLast30DaysVisits() {
        List<Posjeta> posjete = posjetaService.getAllPosjete();

        LocalDate today = LocalDate.now(ZoneId.of("UTC"));
        LocalDate startDate = today.minusDays(29);

        Map<LocalDate, Long> dailyCounts = posjete.stream()
                .filter(p -> {
                    LocalDate date = p.getVrijeme().atZone(ZoneId.of("UTC")).toLocalDate();
                    return !date.isBefore(startDate);
                })
                .collect(Collectors.groupingBy(
                        p -> p.getVrijeme().atZone(ZoneId.of("UTC")).toLocalDate(),
                        Collectors.counting()
                ));


        List<Map<String, Object>> result = startDate.datesUntil(today.plusDays(1))
                .map(date -> {
                    Map<String, Object> m = new HashMap<>();
                    m.put("date", date.toString());
                    m.put("pageviews", dailyCounts.getOrDefault(date, 0L));
                    return m;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }


}
