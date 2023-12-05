package com.autoTech.autoTech.Mapper;
import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.Specializations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecializationMapper {

    @Mapping(target = "specialization", source = "dto.specialization")
    Specializations convertDtoToEntity(SpecializationsDto dto, Long id);
}
