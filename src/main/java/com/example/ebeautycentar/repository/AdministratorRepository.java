package com.example.ebeautycentar.repository;

import com.example.ebeautycentar.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    Optional<Administrator> findByKorisnickoIme(String korisnickoIme);
}