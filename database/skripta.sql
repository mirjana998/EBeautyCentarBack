-- MySQL Workbench Synchronization
-- Generated: 2025-09-12 13:42
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: LENOVO

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

ALTER SCHEMA `ebeautycentardatabase`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`registrovani_klijent` 
DROP FOREIGN KEY `fk_registrovani_klijent_korisnik1`;

ALTER TABLE `ebeautycentardatabase`.`salon` 
DROP FOREIGN KEY `fk_salon_lokacija1`;

ALTER TABLE `ebeautycentardatabase`.`vlasnik_salona` 
DROP FOREIGN KEY `fk_vlasnik_salona_korisnik1`;

ALTER TABLE `ebeautycentardatabase`.`rezervacija` 
DROP FOREIGN KEY `fk_rezervacija_registrovani_klijent1`,
DROP FOREIGN KEY `fk_rezervacija_zaposleni_salon_usluga1`;

ALTER TABLE `ebeautycentardatabase`.`slika` 
DROP FOREIGN KEY `fk_slika_salon1`,
DROP FOREIGN KEY `fk_slika_usluga1`;

ALTER TABLE `ebeautycentardatabase`.`novost` 
DROP FOREIGN KEY `fk_novost_salon1`,
DROP FOREIGN KEY `fk_novost_vlasnik_salona1`;

ALTER TABLE `ebeautycentardatabase`.`salon_usluga` 
DROP FOREIGN KEY `fk_salon_usluga_salon1`,
DROP FOREIGN KEY `fk_salon_usluga_usluga1`;

ALTER TABLE `ebeautycentardatabase`.`radno_vrijeme` 
DROP FOREIGN KEY `fk_radno_vrijeme_dan_u_sedmici1`,
DROP FOREIGN KEY `fk_radno_vrijeme_salon1`;

ALTER TABLE `ebeautycentardatabase`.`ocjena_pružene_usluge` 
DROP FOREIGN KEY `fk_ocjena_pružene_usluge_registrovani_klijent1`,
DROP FOREIGN KEY `fk_ocjena_pružene_usluge_rezervacija1`;

ALTER TABLE `ebeautycentardatabase`.`ocjena_klijenta` 
DROP FOREIGN KEY `fk_ocjena_klijenta_registrovani_klijent1`;

ALTER TABLE `ebeautycentardatabase`.`notifikacija` 
DROP FOREIGN KEY `fk_notifikacija_korisnik1`;

ALTER TABLE `ebeautycentardatabase`.`zaposleni` 
DROP FOREIGN KEY `fk_zaposleni_salon1`,
DROP FOREIGN KEY `fk_zaposleni_vlasnik_salona1`;

ALTER TABLE `ebeautycentardatabase`.`zaposleni_salon_usluga` 
DROP FOREIGN KEY `fk_salon_usluga_has_salon_usluga_zaposleni1`,
DROP FOREIGN KEY `fk_zaposleni_salon_usluga_salon_usluga1`;

ALTER TABLE `ebeautycentardatabase`.`transakcija` 
DROP FOREIGN KEY `fk_transakcija_rezervacija1`;

ALTER TABLE `ebeautycentardatabase`.`korisnik` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`registrovani_klijent` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`salon` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`tip` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`usluga` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`vlasnik_salona` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`rezervacija` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`slika` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`novost` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`salon_usluga` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`administrator` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`radno_vrijeme` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`ocjena_pružene_usluge` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`lokacija` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`ocjena_klijenta` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`notifikacija` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`dan_u_sedmici` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`zaposleni` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`zaposleni_salon_usluga` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ;

ALTER TABLE `ebeautycentardatabase`.`transakcija` 
CHARACTER SET = utf8 , COLLATE = utf8_general_ci ,
CHANGE COLUMN `iznos` `iznos` DECIMAL NOT NULL ;

ALTER TABLE `ebeautycentardatabase`.`registrovani_klijent` 
ADD CONSTRAINT `fk_registrovani_klijent_korisnik1`
  FOREIGN KEY (`korisnik_id`)
  REFERENCES `ebeautycentardatabase`.`korisnik` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`salon` 
DROP FOREIGN KEY `fk_saloon_tip1`,
DROP FOREIGN KEY `fk_salon_vlasnik_salona1`;

ALTER TABLE `ebeautycentardatabase`.`salon` ADD CONSTRAINT `fk_saloon_tip1`
  FOREIGN KEY (`tip_id`)
  REFERENCES `ebeautycentardatabase`.`tip` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_salon_vlasnik_salona1`
  FOREIGN KEY (`vlasnik_salona_id`)
  REFERENCES `ebeautycentardatabase`.`vlasnik_salona` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_salon_lokacija1`
  FOREIGN KEY (`lokacija_id`)
  REFERENCES `ebeautycentardatabase`.`lokacija` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`vlasnik_salona` 
ADD CONSTRAINT `fk_vlasnik_salona_korisnik1`
  FOREIGN KEY (`korisnik_id`)
  REFERENCES `ebeautycentardatabase`.`korisnik` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`rezervacija` 
DROP FOREIGN KEY `fk_zahtjev_vlasnik_salona1`;

ALTER TABLE `ebeautycentardatabase`.`rezervacija` ADD CONSTRAINT `fk_zahtjev_vlasnik_salona1`
  FOREIGN KEY (`vlasnik_salona_id`)
  REFERENCES `ebeautycentardatabase`.`vlasnik_salona` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_rezervacija_registrovani_klijent1`
  FOREIGN KEY (`registrovani_klijent_id`)
  REFERENCES `ebeautycentardatabase`.`registrovani_klijent` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_rezervacija_zaposleni_salon_usluga1`
  FOREIGN KEY (`zaposleni_salon_usluga_id`)
  REFERENCES `ebeautycentardatabase`.`zaposleni_salon_usluga` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`slika` 
ADD CONSTRAINT `fk_slika_salon1`
  FOREIGN KEY (`salon_id`)
  REFERENCES `ebeautycentardatabase`.`salon` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_slika_usluga1`
  FOREIGN KEY (`usluga_id`)
  REFERENCES `ebeautycentardatabase`.`usluga` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`novost` 
ADD CONSTRAINT `fk_novost_salon1`
  FOREIGN KEY (`salon_id`)
  REFERENCES `ebeautycentardatabase`.`salon` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_novost_vlasnik_salona1`
  FOREIGN KEY (`vlasnik_salona_id`)
  REFERENCES `ebeautycentardatabase`.`vlasnik_salona` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`salon_usluga` 
ADD CONSTRAINT `fk_salon_usluga_salon1`
  FOREIGN KEY (`salon_id`)
  REFERENCES `ebeautycentardatabase`.`salon` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_salon_usluga_usluga1`
  FOREIGN KEY (`usluga_id`)
  REFERENCES `ebeautycentardatabase`.`usluga` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`radno_vrijeme` 
ADD CONSTRAINT `fk_radno_vrijeme_dan_u_sedmici1`
  FOREIGN KEY (`dan_u_sedmici_id`)
  REFERENCES `ebeautycentardatabase`.`dan_u_sedmici` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_radno_vrijeme_salon1`
  FOREIGN KEY (`salon_id`)
  REFERENCES `ebeautycentardatabase`.`salon` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`ocjena_pružene_usluge` 
ADD CONSTRAINT `fk_ocjena_pružene_usluge_registrovani_klijent1`
  FOREIGN KEY (`registrovani_klijent_id`)
  REFERENCES `ebeautycentardatabase`.`registrovani_klijent` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_ocjena_pružene_usluge_rezervacija1`
  FOREIGN KEY (`rezervacija_id`)
  REFERENCES `ebeautycentardatabase`.`rezervacija` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`ocjena_klijenta` 
DROP FOREIGN KEY `fk_vlasnik_salona_ocjena_registrovani_klijent_vlasnik_salona1`;

ALTER TABLE `ebeautycentardatabase`.`ocjena_klijenta` ADD CONSTRAINT `fk_vlasnik_salona_ocjena_registrovani_klijent_vlasnik_salona1`
  FOREIGN KEY (`vlasnik_salona_id`)
  REFERENCES `ebeautycentardatabase`.`vlasnik_salona` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_ocjena_klijenta_registrovani_klijent1`
  FOREIGN KEY (`registrovani_klijent_id`)
  REFERENCES `ebeautycentardatabase`.`registrovani_klijent` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`notifikacija` 
ADD CONSTRAINT `fk_notifikacija_korisnik1`
  FOREIGN KEY (`korisnik_id`)
  REFERENCES `ebeautycentardatabase`.`korisnik` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`zaposleni` 
ADD CONSTRAINT `fk_zaposleni_salon1`
  FOREIGN KEY (`salon_id`)
  REFERENCES `ebeautycentardatabase`.`salon` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_zaposleni_vlasnik_salona1`
  FOREIGN KEY (`vlasnik_salona_id`)
  REFERENCES `ebeautycentardatabase`.`vlasnik_salona` (`korisnik_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`zaposleni_salon_usluga` 
ADD CONSTRAINT `fk_salon_usluga_has_salon_usluga_zaposleni1`
  FOREIGN KEY (`zaposleni_id`)
  REFERENCES `ebeautycentardatabase`.`zaposleni` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_zaposleni_salon_usluga_salon_usluga1`
  FOREIGN KEY (`salon_usluga_id`)
  REFERENCES `ebeautycentardatabase`.`salon_usluga` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `ebeautycentardatabase`.`transakcija` 
ADD CONSTRAINT `fk_transakcija_rezervacija1`
  FOREIGN KEY (`rezervacija_id`)
  REFERENCES `ebeautycentardatabase`.`rezervacija` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
