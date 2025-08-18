-- triger azurira prosjecnu ocjenu salona, nakon sto neki od klijenata doda ocjenu pruzene usluge
DELIMITER $$
create trigger trg_ocjena_salona 
after insert on ocjena_pružene_usluge
for each row
begin

	declare salon_id_val bigint;
    
    select su.salon_id into salon_id_val
    from ocjena_pružene_usluge opu
    join rezervacija rez on opu.rezervacija_id = rez.id
    join zaposleni_salon_usluga zsu on zsu.id = rez.zaposleni_salon_usluga_id
    join salon_usluga su ON zsu.salon_usluga_id = su.id
    where opu.id = NEW.id; 
    
	update salon
	set prosjecna_ocjena = (
    select avg(opu.ocjena)
    from ocjena_pružene_usluge opu
    join rezervacija rez ON opu.rezervacija_id = rez.id
	join zaposleni_salon_usluga zsu on zsu.id = rez.zaposleni_salon_usluga_id
    join salon_usluga su ON zsu.salon_usluga_id = su.id
    where su.salon_id = salon_id_val
  )where id = salon_id_val;
end$$
DELIMITER ;


DELIMITER $$

create trigger trg_postavi_vrijeme_zavrsetka
before update on salon_usluga
for each row
begin
  if new.status = 'N' and old.status != 'N' then
    set new.datum_kraja = NOW();
  end if;
end$$

DELIMITER ;
