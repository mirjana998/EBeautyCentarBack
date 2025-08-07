package com.example.ebeautycentar.controller;

import com.example.ebeautycentar.entity.RadnoVrijeme;
import com.example.ebeautycentar.entity.Rezervacija;
import com.example.ebeautycentar.entity.SalonUsluga;
import com.example.ebeautycentar.entity.Zaposleni;
import com.example.ebeautycentar.service.RadnoVrijemeService;
import com.example.ebeautycentar.service.RezervacijaService;
import com.example.ebeautycentar.service.SalonUslugaService;
import com.example.ebeautycentar.service.ZaposleniService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rezervacija")
public class RezervacijaController {

    public final RezervacijaService rezervacijaService;
    public final SalonUslugaService salonUslugaService;
    public final RadnoVrijemeService radnoVrijemeService;
    public final ZaposleniService zaposleniService;

    public RezervacijaController(RezervacijaService rezervacijaService, SalonUslugaService salonUslugaService, RadnoVrijemeService radnoVrijemeService, ZaposleniService zaposleniService) {
        this.rezervacijaService = rezervacijaService;
        this.salonUslugaService = salonUslugaService;
        this.radnoVrijemeService = radnoVrijemeService;
        this.zaposleniService = zaposleniService;
    }

    public static boolean istiDan(Instant instant1, Instant instant2) {
        ZoneId zoneId = ZoneId.of("Europe/Belgrade");
        LocalDate date1 = instant1.atZone(zoneId).toLocalDate();
        LocalDate date2 = instant2.atZone(zoneId).toLocalDate();
        System.out.println("Date1: " + date1 + ", Date2: " + date2 + " E: " + date1.isEqual(date2) );
        return date1.isEqual(date2);
    }

    public static boolean uporediVremenaRezervacije(Rezervacija zakazanaRezervacija, Instant pocetakUpitneRezervacije, Instant zavrsetakUpitneRezervacije) {
        SalonUsluga uslugaZakazaneRezervacije = zakazanaRezervacija.getZaposleniSalonUsluga().getSalonUsluga();
        Instant pocetakZakazaneRezervacije = zakazanaRezervacija.getTerminPocetkaUsluge();
        Instant zavrsetakZakazaneRezervacije = pocetakZakazaneRezervacije.plus(uslugaZakazaneRezervacije.getTrajanjeUsluge().getHour(),ChronoUnit.HOURS).plus(uslugaZakazaneRezervacije.getTrajanjeUsluge().getMinute(),ChronoUnit.MINUTES).plus(uslugaZakazaneRezervacije.getTrajanjeUsluge().getSecond(),ChronoUnit.SECONDS);
        if(zavrsetakUpitneRezervacije.isBefore(pocetakZakazaneRezervacije) || pocetakUpitneRezervacije.isAfter(zavrsetakZakazaneRezervacije))
            return false;
        return true;
    }

    public static boolean provjeriRadnoVrijeme(LocalTime pocetakRadnoVrijeme,LocalTime krajRadnoVrijeme, Instant pocetakUpitneRezervacije, Instant zavrsetakUpitneRezervacije) {
        ZoneId zoneId = ZoneId.of("Europe/Belgrade");
        LocalTime vrijemePocetka = LocalTime.ofInstant(pocetakUpitneRezervacije, zoneId);
        LocalTime vrijemeZavrsetka = LocalTime.ofInstant(zavrsetakUpitneRezervacije, zoneId);
        if(pocetakRadnoVrijeme.isAfter(vrijemePocetka) || krajRadnoVrijeme.isBefore(vrijemeZavrsetka))
            return false;
        return true;
    }


    @GetMapping("/provjera")
    public ResponseEntity<String> provjeriSlobodnuRezervaciju(@RequestParam Long usluga, @RequestParam Long zaposleni, @RequestParam("datum") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant datum) {

//        System.out.println("usluga: " + usluga);
//        System.out.println("zaposleni: " + zaposleni);
//        System.out.println("datum pocetka: " + datum);

        Optional<SalonUsluga> salonUslugaOptional = salonUslugaService.getSalonUslugaById(usluga);
        Optional<Zaposleni> zaposleniOptional = zaposleniService.getZaposleniById(zaposleni);
        if (salonUslugaOptional.isPresent() && zaposleniOptional.isPresent()) {
            SalonUsluga salonUsluga = salonUslugaOptional.get();

            // prvo odredi vrijeme zavrsetka rezervacije za koju se salje upit
            Instant datumZavrsetkaRezervacije = datum.plus(salonUsluga.getTrajanjeUsluge().getHour(), ChronoUnit.HOURS).plus(salonUsluga.getTrajanjeUsluge().getMinute(), ChronoUnit.MINUTES).plus(salonUsluga.getTrajanjeUsluge().getSecond(), ChronoUnit.SECONDS);
            System.out.println("planirani datum zavrsetka: " + datumZavrsetkaRezervacije);
//            RadnoVrijeme radnoVrijeme = radnoVrijemeService.findRadnoVrijemeByDanUSedmiciIdAndSalonId(datum.atZone(ZoneId.systemDefault()).getDayOfWeek().getValue(),salonUsluga.getSalon().getId());
//            if(!provjeriRadnoVrijeme(radnoVrijeme.getPocetakRadnoVrijeme(), radnoVrijeme.getKrajRadnoVrijeme(),datum, datumZavrsetkaRezervacije)) {
//                return ResponseEntity.notFound().build();
//            }

            List<Rezervacija> rezervacijeZaposlenog = rezervacijaService.getRezervacijaByZaposleniId(zaposleni);

            List<Rezervacija> rezervacijeIstiDan = rezervacijeZaposlenog.stream().filter(rezervacija ->
                    istiDan(rezervacija.getTerminPocetkaUsluge(), datum)).toList();
//            rezervacijeIstiDan.stream().forEach(rezervacija -> {
//                System.out.println("Dan: " + rezervacija.getId() + " " + rezervacija.getZaposleniSalonUsluga().getZaposleni().getId() + " " + rezervacija.getTerminPocetkaUsluge());
//            });

            List<Rezervacija> preklapajuceRezervacije = rezervacijeIstiDan.stream().filter(rezervacija ->
                    uporediVremenaRezervacije(rezervacija, datum, datumZavrsetkaRezervacije)).toList();
            preklapajuceRezervacije.stream().forEach(rezervacija -> {
                System.out.println("Preklapanje: " + rezervacija.getId() + " " + rezervacija.getZaposleniSalonUsluga().getZaposleni().getId() + " " + rezervacija.getTerminPocetkaUsluge());
            });
            if (preklapajuceRezervacije.isEmpty()) {
                return ResponseEntity.ok("SLOBODAN TERMIN!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NIJE SLOBODAN TERMIN!");
            }
        } else if (zaposleniOptional.isPresent() && salonUslugaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USLUGA NIJE PRONADJENA!");
        } else if (zaposleniOptional.isEmpty() && salonUslugaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ZAPOSLENI NIJE PRONADJEN!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USLUGA I ZAPOSLENI NISU PRONADJENI!");
        }

    }

}
