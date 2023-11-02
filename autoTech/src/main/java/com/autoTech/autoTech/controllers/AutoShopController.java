package com.autoTech.autoTech.controllers;

import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.services.AutoShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class AutoShopController {
    private final AutoShopService autoShopService;
    @Autowired
    public AutoShopController(AutoShopService autoShopService) {
        this.autoShopService = autoShopService;
    }
    @GetMapping("/fetch/autoShop")
    public List<AutoShop> fetchAutoShops() {
        return autoShopService.getAllShops();
    }
    @PostMapping("/save/autoShop")
    public ResponseEntity<?> saveService(@RequestBody AutoShopDto autoShopDto) {
        AutoShop savedInDb = autoShopService.saveShop(autoShopDto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }
//    @GetMapping("/filter")
//    public List<AutoShop> filterShops(String serviceSpec){
//        return autoShopService
//    }


}
