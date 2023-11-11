package com.autoTech.autoTech.controllers;

import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.models.Specializations;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import com.autoTech.autoTech.repositories.SpecializationsRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/specialization")
@CrossOrigin("*")
public class ShopSpecializationController {
    private final AutoShopRepo autoShopRepo;
    private final SpecializationsRepo specializationsRepo;

    public ShopSpecializationController(AutoShopRepo autoShopRepo,
                                        SpecializationsRepo specializationsRepo) {
        this.autoShopRepo = autoShopRepo;
        this.specializationsRepo = specializationsRepo;
    }

    @PostMapping("/save")
    public ResponseEntity<?> persistSpecialization(String specializationName){
        Specializations specializations = specializationsRepo.findSpecializationBySpecializationName(specializationName);
        if(specializations!=null){
            return ResponseEntity.ok("Specialization exists");
        }

        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);    }
    @PostMapping("/save")
    public ResponseEntity<?> saveSpecialization(@RequestBody SpecializationsDto dto) {
        AutoShop savedInDb = SpecializationsService.saveShop(dto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }
}
