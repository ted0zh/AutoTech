package com.autoTech.autoTech.services;

import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AutoShopService {
    private final AutoShopRepo autoShopRepo;

@Autowired
    public AutoShopService(AutoShopRepo autoShopRepo) {
        this.autoShopRepo = autoShopRepo;
    }

    public List<AutoShop> getAllShops(){
        return autoShopRepo.findAll();
    }
    public AutoShop saveShop(AutoShopDto autoShopDto) {
        Optional<AutoShop> dbObject = autoShopRepo.findAutoShopByShopName(autoShopDto.getShopName());
        AutoShop autoShop = dbObject.orElseGet(AutoShop::new); // Use orElseGet

        autoShop.setShopName(autoShopDto.getShopName());
        autoShop.setEmailShop(autoShopDto.getEmailShop());
        autoShop.setPhoneNumber(autoShopDto.getPhoneNumber());
        autoShop.setLocation(autoShopDto.getLocation()); // Correct the property name
        autoShop.setInfo(autoShopDto.getInfo());

        return autoShopRepo.save(autoShop);
    }

}
