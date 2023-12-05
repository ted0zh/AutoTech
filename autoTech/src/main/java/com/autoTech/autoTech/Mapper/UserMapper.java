package com.autoTech.autoTech.Mapper;
import com.autoTech.autoTech.dto.UserDto;
import com.autoTech.autoTech.models.Users;
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
