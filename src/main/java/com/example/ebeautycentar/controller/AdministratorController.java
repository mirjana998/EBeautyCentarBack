package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.AdministratorDto;
import com.example.ebeautycentar.dto.AdministratorLoginDto;
import com.example.ebeautycentar.dto.VlasnikSalonaUpdateDto;
import com.example.ebeautycentar.entity.VlasnikSalona;
import com.example.ebeautycentar.service.AdministratorService;
import com.example.ebeautycentar.service.VlasnikSalonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private final AdministratorService administratorService;

    @Autowired
    private final VlasnikSalonaService vlasnikSalonaService;

    @Autowired
    public AdministratorController(AdministratorService administratorService, VlasnikSalonaService vlasnikSalonaService) {
        this.administratorService = administratorService;
        this.vlasnikSalonaService=vlasnikSalonaService;
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> prijavaAdministratora(@RequestBody AdministratorLoginDto dto) {
//        Optional<Administrator> administratorOptional = administratorService.findByKorisnickoIme(dto.getUsername());
//
//        if (administratorOptional.isPresent()) {
//            Administrator administrator = administratorOptional.get();
//
//            if (administrator.getLozinka().equals(dto.getPassword())) {
//                return ResponseEntity.ok(new AdministratorDto(administrator));
//            } else {
//                return ResponseEntity.status(401).body("Pogrešna lozinka");
//            }
//        }
//
//        return ResponseEntity.status(404).body("Administrator nije pronađen");
//    }
//
//    @GetMapping("/provjera")
//    public ResponseEntity<String> getProvjera(@RequestParam String username, @RequestParam String password) {
//        Map map = Role.procedura(username, password, dataSource);
//        System.out.println(map.get("rola"));
//        System.out.println(map.get("result"));
//        System.out.println(administratorService.findByKorisnickoIme("admin"));
//        return ResponseEntity.ok("administrator");
//    }

    @PutMapping("/vlasnik/{vlasnikId}")
    public ResponseEntity<?> updateVlasnik(
            @PathVariable Long vlasnikId,
            @RequestParam String ime,
            @RequestParam String prezime,
            @RequestParam String korisnickoIme,
            @RequestParam String brojTelefona,
            @RequestParam String email
    ) {
        try {
            VlasnikSalona updated = vlasnikSalonaService.updateVlasnik(vlasnikId, ime, prezime,korisnickoIme, brojTelefona, email);
            return ResponseEntity.ok(new VlasnikSalonaUpdateDto(updated));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("poruka", e.getMessage()));
        }
    }

}
