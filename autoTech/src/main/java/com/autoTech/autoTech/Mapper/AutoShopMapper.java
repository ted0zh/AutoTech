package com.autoTech.autoTech.Mapper;
import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutoShopMapper {
    @Mapping(target = "shopName", source = "dto.shopName")
    @Mapping(target = "emailShop", source = "dto.emailShop")
    @Mapping(target = "phoneNumber", source = "dto.phoneNumber")
    @Mapping(target = "location", source = "dto.location")
    @Mapping(target = "info", source = "dto.info")
    AutoShop convertDtoToEntity(AutoShopDto dto, Long id);
}
//    private String shopName;
//    private String emailShop;
//    private String phoneNumber;
//    private String location;
//    private String info;