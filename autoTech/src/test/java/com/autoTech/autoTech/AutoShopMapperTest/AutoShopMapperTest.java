package com.autoTech.autoTech.AutoShopMapperTest;

import com.autoTech.autoTech.data.mapper.AutoShopMapper;
import com.autoTech.autoTech.data.dto.AutoShopDto;
import com.autoTech.autoTech.data.models.AutoShop;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

     class AutoShopMapperTest {

         private AutoShopMapper mapper = Mappers.getMapper(AutoShopMapper.class);

         @Test
         void testConvertDtoToEntity() {
             AutoShopDto dto = new AutoShopDto("NSN", "nsn.tuning@abv.bg", "0888555555", "Sofia", "Newbies");

             AutoShop autoShop = mapper.convertDtoToEntity(dto, 1L);

             assertEquals("NSN", autoShop.getShopName());
             assertEquals("nsn.tuning@abv.bg", autoShop.getEmailShop());
             assertEquals("0888555555", autoShop.getPhoneNumber());
             assertEquals("Sofia", autoShop.getLocation());
             assertEquals("Newbies", autoShop.getInfo());
             assertEquals(1L, autoShop.getId());
         }
     }


