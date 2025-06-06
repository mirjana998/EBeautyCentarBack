package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.dto.KorisnikDto;
import com.example.ebeautycentar.entity.Korisnik;
import com.example.ebeautycentar.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/korisnik")
public class KorisnikController {

    private final KorisnikService korisnikService;

    @Autowired
    public KorisnikController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping
    public List<KorisnikDto> getAllKorisnik() {
        return korisnikService.getAllKorisnik();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KorisnikDto> getKorisnikById(@PathVariable Long id) {
        Optional<Korisnik> korisnik = korisnikService.getKorisnikById(id);
        if(korisnik.isPresent()) {
            KorisnikDto korisnikDto = new KorisnikDto(korisnik.get());
            return ResponseEntity.ok(korisnikDto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKorisnik(@PathVariable Long id) {
        korisnikService.deleteKorisnik(id);
        return ResponseEntity.ok("OBRISAN!");
    }

//    @PostMapping("/new/{autonomy}")
//    public ResponseEntity<BicycleDto> createBicycle(@RequestBody Vehicle vehicle, @PathVariable double autonomy) {
//        System.out.println(vehicle.getId() + " " + autonomy);
//        Optional<Manufacturer> manufacturer = manufacturerService.getManufacturerById(vehicle.getManufacturer().getId());
//        Optional<Photo> photo = photoService.getPhotoById(vehicle.getPhoto().getId());
//        Vehicle newVehicle = new Vehicle();
//        Bicycle newBicycle = new Bicycle();
//        if(manufacturer.isPresent() && photo.isPresent()) {
//            newVehicle.setVehicle(vehicle,manufacturer.get(),photo.get());
//            newBicycle.setVehicle(vehicleService.saveVehicle(newVehicle));
//            newBicycle.setAutonomy(autonomy);
//            bicycleService.saveBicycle(newBicycle);
//        }
//        return ResponseEntity.ok(new BicycleDto(newBicycle));
//    }
//
//    @GetMapping("/pagination/{offset}/{pageSize}")
//    Page<BicycleDto> getManufacturerWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
//        Page<Bicycle> bicycles =  bicycleService.getBicycleWithPagination(offset,pageSize);
//        return bicycles.map(BicycleDto::new);
//    }


//    @PutMapping("/active")
//    public ResponseEntity<BicycleDto> updateBicycleActive(@RequestBody Bicycle bicycle) {
//        Optional<Bicycle> oldBicycle = bicycleService.getBicycleById(bicycle.getId());
//        if (oldBicycle.isPresent()) {
//            vehicleService.updateVehicleActive(bicycle.getVehicle().getActive(), oldBicycle.get().getId());
//            oldBicycle = bicycleService.getBicycleById(bicycle.getId());
//            if(oldBicycle.isPresent()) {
//                BicycleDto bicycleDto = new BicycleDto(oldBicycle.get());
//                return ResponseEntity.ok(bicycleDto);
//            }
//            return ResponseEntity.notFound().build();
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}

