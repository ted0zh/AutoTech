package com.autoTech.autoTech.controllers;

import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.services.AutoShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class AutoShopController {
    private final AutoShopService autoShopService;

    @Autowired
    public AutoShopController(AutoShopService autoShopService) {
        this.autoShopService = autoShopService;
    }
    @GetMapping("/fetch")
    public List<AutoShop> fetchAutoShops() {
        return autoShopService.getAllShops();
    }
    @PostMapping("/save")
    public ResponseEntity<?> saveAutoShop(@RequestBody AutoShopDto autoShopDto) {
        AutoShop savedInDb = autoShopService.saveShop(autoShopDto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAutoShop(@PathVariable("id") Long id) {
        autoShopService.deleteAutoShop(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/filter/{name}")
    public Optional<AutoShop> filterAutoShops(@PathVariable("name") String shopName) {

        return autoShopService.filterAutoShops(shopName);

    }
//    @GetMapping("/filter/{specialization}")
//    public Optional<AutoShop> filterAutoShopsBySpecializations(@PathVariable("specialization") String specialization) {
//
//        return autoShopService.filterAutoShopsBySpecializations(specialization);
//
//    }



}
