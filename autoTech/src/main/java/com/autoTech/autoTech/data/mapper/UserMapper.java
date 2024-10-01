package com.autoTech.autoTech.data.mapper;
import com.autoTech.autoTech.data.dto.UserDto;
import com.autoTech.autoTech.data.models.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "userMail", source = "dto.userMail")
    @Mapping(target = "userNumber", source = "dto.userNumber")
    Users convertDtoToEntity(UserDto dto, Long id);


}
