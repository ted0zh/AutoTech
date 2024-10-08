package com.autoTech.autoTech.controllers;

import com.autoTech.autoTech.data.dto.AutoShopDto;
import com.autoTech.autoTech.data.models.AutoShop;
import com.autoTech.autoTech.services.AutoShopService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

@GetMapping("/page/shops")
public ResponseEntity<Map<String, Object>> fetch(
        @RequestParam(required = false, defaultValue = "1") int currentPage,
        @RequestParam(required = false, defaultValue = "2") int perPage
) {
    Pageable pageable = PageRequest.of(currentPage - 1, perPage);
    Page<AutoShop> page = autoShopService.getAllAutoShops(pageable);
    Map<String, Object> response = Map.of(
            "auto-shops", page.getContent(),
            "totalPages", page.getTotalPages(),
            "totalElements", page.getTotalElements()
    );
    return new ResponseEntity<>(response, HttpStatus.OK);
}
    @PutMapping("/{shopId}/specifications/{specializationId}")

    public ResponseEntity<?> addSpecializationToAutoShop(@PathVariable Long shopId, @PathVariable Long specializationId) {

        try {
            autoShopService.addSpecializationToAutoShop(shopId,specializationId);

            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
