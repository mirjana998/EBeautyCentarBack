package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.RegistrovaniKlijentDto;
import com.example.ebeautycentar.entity.RegistrovaniKlijent;
import com.example.ebeautycentar.service.RegistrovaniKlijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/registrovani_klijent")
public class RegistrovaniKlijentContoller {

    private final RegistrovaniKlijentService registrovaniKlijentService;

    @Autowired
    public RegistrovaniKlijentContoller(RegistrovaniKlijentService registrovaniKlijentService) {
        this.registrovaniKlijentService = registrovaniKlijentService;
    }

    @GetMapping
    public List<RegistrovaniKlijentDto> getAllRegistrovaniKlijent() {
        return registrovaniKlijentService.getAllRegistrovaniKlijent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrovaniKlijentDto> getKorisnikById(@PathVariable Long id) {
        Optional<RegistrovaniKlijent> registrovaniKlijent = registrovaniKlijentService.getRegistrovaniKlijentById(id);
        if(registrovaniKlijent.isPresent()) {
            RegistrovaniKlijentDto registrovaniKlijentDto = new RegistrovaniKlijentDto(registrovaniKlijent.get());
            return ResponseEntity.ok(registrovaniKlijentDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
