package com.autoTech.autoTech.AutoShopMapperTest;

import com.autoTech.autoTech.Mapper.SpecializationMapper;
import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.Specializations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class SpecializationMapperTest {
    private final SpecializationMapper underTest = Mappers.getMapper(SpecializationMapper.class);

    @ParameterizedTest
    @MethodSource("paramProvider")
    void convertDtoToEntityTest(SpecializationsDto dto, String[] emptyFields) {
        Specializations result = underTest.convertDtoToEntity(dto, 1L);
        assertThat(result)
                .isNotNull()
                .hasNoNullFieldsOrPropertiesExcept(emptyFields);
    }

    private static Stream<Arguments> paramProvider() {
        return Stream.of(
                Arguments.of(
                        new SpecializationsDto("Tuning"),
                        new String[]{"specialization"}
                ),
                Arguments.of(
                        new SpecializationsDto(null),
                        new String[]{"specialization"}
                )

        );
    }
}
