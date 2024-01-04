package com.autoTech.autoTech.controllers;


import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.Specializations;
import com.autoTech.autoTech.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specialization")
public class ShopSpecializationController {

    private final SpecializationService specializationService;

    @Autowired
    public ShopSpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping("/fetch")
    public List<Specializations> getAllSpecializations(){
        return specializationService.getAllSpecializations();
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveSpecialization(@RequestBody SpecializationsDto dto) {
        Specializations savedInDb = specializationService.saveSpecialization(dto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{specialization}")
    public ResponseEntity<?> deleteSpecialization(@PathVariable("specialization") String specialization) {
        specializationService.deleteBySpecialization(specialization);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter/{specialization}")
    public Optional<Specializations> filterSpecializationType(@PathVariable("specialization") String specialization) {
        return specializationService.filterSpecializations(specialization);
    }
}
