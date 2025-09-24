package com.example.ebeautycentar.service;

import com.example.ebeautycentar.entity.Administrator;
import com.example.ebeautycentar.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorService {

    @Autowired
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

//    public List<AdministratorDto> getAllAdministrator() {
//        List<Administrator> AdministratorList = administratorRepository.findAll();
//        List<AdministratorDto> AdministratorDtoList = new ArrayList<>();
//        for (Administrator administrator : AdministratorList) {
//            AdministratorDtoList.add(new AdministratorDto(administrator));
//        }
//        return AdministratorDtoList;
//    }

    public Optional<Administrator> findById(Integer id) {
        return administratorRepository.findById(id);
    }


    public Optional<Administrator> getAdministratorById(Integer id) {
        return administratorRepository.findById(id);
    }


    public Administrator saveAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    public void deleteAdministrator(Integer id) {
        administratorRepository.deleteById(id);
    }

}
