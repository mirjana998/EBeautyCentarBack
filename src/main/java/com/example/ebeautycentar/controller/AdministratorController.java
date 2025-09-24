package com.example.ebeautycentar.controller;


import com.example.ebeautycentar.dto.AdministratorDto;
import com.example.ebeautycentar.dto.AdministratorLoginDto;
import com.example.ebeautycentar.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
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

}
