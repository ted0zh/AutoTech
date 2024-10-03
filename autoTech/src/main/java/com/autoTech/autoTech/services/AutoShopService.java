package com.autoTech.autoTech.services;

import com.autoTech.autoTech.data.mapper.AutoShopMapper;
import com.autoTech.autoTech.data.dto.AutoShopDto;
import com.autoTech.autoTech.data.models.AutoShop;
import com.autoTech.autoTech.data.models.Specializations;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import com.autoTech.autoTech.repositories.SpecializationsRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutoShopService {
    private final AutoShopRepo autoShopRepo;
    private final SpecializationsRepo specializationsRepo;
    private final AutoShopMapper autoShopMapper;
@Autowired
    public AutoShopService(AutoShopRepo autoShopRepo,AutoShopMapper mapper,SpecializationsRepo specializationsRepo) {
        this.autoShopRepo = autoShopRepo;
        this.autoShopMapper = mapper;
        this.specializationsRepo=specializationsRepo;
    }
    public Page<AutoShop> getAllAutoShops(Pageable pageable){
        return autoShopRepo.findAll(pageable);
    }
    public List<AutoShop> getAllShops(){
        return autoShopRepo.findAll();
    }
    public AutoShop saveShop(AutoShopDto autoShopDto) {
        Optional<AutoShop> dbObject = autoShopRepo.findAutoShopByShopName(autoShopDto.shopName());
        Long id;
        if(dbObject.isPresent()) {
            id = dbObject.get().getId();
        } else {
            id = null;
        }
        AutoShop autoShop = autoShopMapper.convertDtoToEntity(autoShopDto, id);
        return autoShopRepo.saveAndFlush(autoShop);
    }
    public void deleteAutoShop(Long id){
        this.autoShopRepo.deleteById(id);
    }

    public Optional<AutoShop> filterAutoShops(String shopName) {
    return autoShopRepo.findAutoShopByShopName(shopName);
    }

    public void addSpecializationToAutoShop(Long shopId, Long specializationId) {
        AutoShop shop = autoShopRepo.findById(shopId).orElseThrow(() -> new EntityNotFoundException(
                "Shop not found with ID: " + shopId));
        Specializations specialization = specializationsRepo.findById(specializationId).orElseThrow(() -> new EntityNotFoundException(
                "Specification not found with ID: " + specializationId));
        shop.getSpecializations().add(specialization);
        autoShopRepo.save(shop);
    }
    
}
