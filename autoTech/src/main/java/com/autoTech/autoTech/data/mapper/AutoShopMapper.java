package com.autoTech.autoTech.data.mapper;
import com.autoTech.autoTech.data.dto.AutoShopDto;
import com.autoTech.autoTech.data.models.AutoShop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutoShopMapper {
    @Mapping(target = "shopName", source = "dto.shopName")
    @Mapping(target = "emailShop", source = "dto.emailShop")
    @Mapping(target = "phoneNumber", source = "dto.phoneNumber")
    @Mapping(target = "location", source = "dto.location")
    @Mapping(target = "info", source = "dto.info")
    AutoShop convertDtoToEntity(AutoShopDto dto, Long id);
}
