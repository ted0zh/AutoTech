package com.autoTech.autoTech.services;

import com.autoTech.autoTech.Mapper.AutoShopMapper;
import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class AutoShopService {
    private final AutoShopRepo autoShopRepo;
    private final AutoShopMapper autoShopMapper;
@Autowired
    public AutoShopService(AutoShopRepo autoShopRepo,AutoShopMapper mapper) {
        this.autoShopRepo = autoShopRepo;
        this.autoShopMapper= mapper;
    }

    public List<AutoShop> getAllShops(){
        return autoShopRepo.findAll();
    }
    public AutoShop saveShop(AutoShopDto autoShopDto) {
        Optional<AutoShop> dbObject = autoShopRepo.findAutoShopByShopName(autoShopDto.getShopName());
        Long id;
        if(dbObject.isPresent()) {
            id = dbObject.get().getId();
            log.info("Updating AutoShop with id {}", id);
        } else {
            id = null;
            log.info("Inserting new AutoShop");
        }
        AutoShop autoShop = autoShopMapper.convertDtoToEntity(autoShopDto, id);
        return autoShopRepo.saveAndFlush(autoShop);


//        Optional<AutoShop> dbObject = autoShopRepo.findAutoShopByShopName(autoShopDto.getShopName());
//        AutoShop autoShop = dbObject.orElseGet(AutoShop::new); // Use orElseGet
//
//        autoShop.setShopName(autoShopDto.getShopName());
//        autoShop.setEmailShop(autoShopDto.getEmailShop());
//        autoShop.setPhoneNumber(autoShopDto.getPhoneNumber());
//        autoShop.setLocation(autoShopDto.getLocation()); // Correct the property name
//        autoShop.setInfo(autoShopDto.getInfo());
//
//        return autoShopRepo.save(autoShop);
    }

}
