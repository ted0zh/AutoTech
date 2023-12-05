package com.autoTech.autoTech.services;

import  com.autoTech.autoTech.Mapper.AutoShopMapper;
import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@Service
public class AutoShopService {
    private static final Logger log = LoggerFactory.getLogger(AutoShopService.class);

    private final AutoShopRepo autoShopRepo;
    private final AutoShopMapper autoShopMapper;
@Autowired
    public AutoShopService(AutoShopRepo autoShopRepo,AutoShopMapper mapper) {
        this.autoShopRepo = autoShopRepo;
        this.autoShopMapper = mapper;
    }

    public List<AutoShop> getAllShops(){
        return autoShopRepo.findAll();
    }
    public AutoShop saveShop(AutoShopDto autoShopDto) {
        Optional<AutoShop> dbObject = autoShopRepo.findAutoShopByShopName(autoShopDto.shopName());
        Long id;
        if(dbObject.isPresent()) {
            id = dbObject.get().getId();
            log.info("Updating autoShop with id {}", id);
        } else {
            id = null;
            log.info("Inserting new autoShop");
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

    public Page<AutoShop> getAllShops(Pageable pageable) {
            return autoShopRepo.findAll(pageable);
    }

    public void addSpecificationToAutoShop(Long shop_id){

    }
//    public Optional<AutoShop> filterAutoShopsBySpecializations(String specialization) {
//        return autoShopRepo.filterAutoShopsBySpecializations(specialization);
//    }
}
