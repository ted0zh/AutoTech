package com.autoTech.autoTech.services;

import com.autoTech.autoTech.Mapper.SpecializationMapper;
import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.models.Specializations;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import com.autoTech.autoTech.repositories.SpecializationsRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {
    private final SpecializationsRepo specializationsRepo;
    private final SpecializationMapper specializationMapper;
    private final AutoShopRepo autoShopRepo;
    private static final Logger log = LoggerFactory.getLogger(AutoShopService.class);

    @Autowired
    public SpecializationService(AutoShopRepo autoShopRepo, SpecializationsRepo specializationsRepo, SpecializationMapper specializationMapper) {
        this.autoShopRepo=autoShopRepo;
        this.specializationsRepo = specializationsRepo;
        this.specializationMapper = specializationMapper;
    }

    public Specializations saveSpecialization(SpecializationsDto dto) {
        Optional<Specializations> dbObject = specializationsRepo.findSpecializationBySpecialization(dto.specialization());
        Long id;
        if(dbObject.isPresent()) {
            id = dbObject.get().getId();
            log.info("Updating specialization with id {}", id);
        } else {
            id = null;
            log.info("Inserting new specialization");
        }
        Specializations specialization = specializationMapper.convertDtoToEntity(dto, id);
        return specializationsRepo.saveAndFlush(specialization);
    }
    public List<Specializations> getAllSpecializations(){
        return this.specializationsRepo.findAll();
    }

    @Transactional
    public void deleteBySpecialization(String specialization){
        this.specializationsRepo.deleteSpecializationBySpecialization(specialization);
    }

//    public void deleteByIdSpecialization(Long id){
//        this.specializationsRepo.deleteById(id);
//    }

    public Optional<Specializations> filterSpecializations(String specialization) {
        return this.specializationsRepo.findSpecializationBySpecialization(specialization);
    }



}
