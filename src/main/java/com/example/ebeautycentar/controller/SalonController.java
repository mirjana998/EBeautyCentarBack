package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.dto.SalonDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.entity.Salon;
import com.example.ebeautycentar.service.SalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/salon")
public class SalonController {

    private final SalonService salonService;

    @Autowired
    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @GetMapping
    public List<SalonDto> getAllSalon() {
        return salonService.getAllSalon();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long id) {
        Optional<Salon> salon = salonService.getSalonById(id);
        if(salon.isPresent()) {
            SalonDto salonDto = new SalonDto(salon.get());
            return ResponseEntity.ok(salonDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalon(@PathVariable Long id) {
        salonService.deleteSalon(id);
        return ResponseEntity.ok("OBRISAN!");
    }

    @GetMapping("/pretraga")
    public ResponseEntity<List<SalonDto>> pretragaSalona(@RequestParam String grad, @RequestParam String usluga,@RequestParam String naziv) {
        if("".equals(grad)&& "".equals(usluga)&&!"".equals(naziv))
        {
            System.out.println("1111111");
            List<SalonDto> saloni = salonService.findByNazivAndStatus(naziv,"A");
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
        else if("".equals(usluga)&&"".equals(naziv)&&!"".equals(grad))
            {
                System.out.println("2222222");
                List<SalonDto> saloni = salonService.findByGradAndStatus(grad,"A");
                if (saloni.isEmpty()) {
                    return ResponseEntity.noContent().build();
                }
                return ResponseEntity.ok(saloni);

            }
        else if("".equals(grad)&&"".equals(naziv)&&!"".equals(usluga)){
            System.out.println("3333333");
            List<SalonDto> saloni = salonService.findByUslugaAndStatus(usluga,"A");
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
        else if(!("".equals(grad)) &&!("".equals(usluga)) && "".equals(naziv)) {
            System.out.println("44444");
            List<SalonDto> saloni = salonService.getSaloniByLokacijaIUsluga(grad, usluga);
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
        else if(!("".equals(grad)) && !("".equals(naziv)) && "".equals(usluga))
        {
            System.out.println("55555");
            List<SalonDto> saloni = salonService.getSaloniByGradINaziv(grad, naziv);
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
        else if(!("".equals(usluga)) && !("".equals(naziv)) && "".equals(grad))
        {
            System.out.println("666666");
            List<SalonDto>saloni=salonService.getSaloniByUslugaAndNaziv(usluga,naziv);
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
        else if(!("".equals(grad))&&!("".equals(naziv))&&!("".equals(usluga)))
        {
            System.out.println("77777");
            List<SalonDto>saloni=salonService.getSaloniByGradAndUslugaAndNaziv(grad,usluga,naziv);
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
        else
        {
            System.out.println("88888");
            List<SalonDto>saloni=salonService.getAllSalon();
            if (saloni.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(saloni);
        }
    }
}
