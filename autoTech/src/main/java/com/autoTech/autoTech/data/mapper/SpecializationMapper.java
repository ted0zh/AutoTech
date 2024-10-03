package com.autoTech.autoTech.data.mapper;
import com.autoTech.autoTech.data.dto.SpecializationsDto;
import com.autoTech.autoTech.data.models.Specializations;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecializationMapper {

    @Mapping(target = "specialization", source = "dto.specialization")
    Specializations convertDtoToEntity(SpecializationsDto dto, Long id);
}
