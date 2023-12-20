package com.autoTech.autoTech.AutoShopMapperTest;

import com.autoTech.autoTech.Mapper.AutoShopMapper;
import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;


import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

 class AutoShopMapperTest {

     private final AutoShopMapper underTest = Mappers.getMapper(AutoShopMapper.class);

     @ParameterizedTest
     @MethodSource("paramProvider")
     void ConvertDtoToEntityTest(AutoShopDto autoShopDto, String[] emptyFields) {
         AutoShop autoshopResult = underTest.convertDtoToEntity(autoShopDto, 2L);
         assertThat(autoshopResult)
                 .isNotNull()
                 .hasAllNullFieldsOrPropertiesExcept(emptyFields);
     }





     private static Stream<Arguments> paramProvider(){
         return Stream.of(
                 Arguments.of(
                         new AutoShopDto("MedinaMed","null", "null", "null", "null"),
                         new String[] { "emailShop", "phoneNumber", "location", "info" }
                 ),
                 Arguments.of(
                         new AutoShopDto("MedinaMed","medinamed@gmail.com", "null", "null", "null"),
                         new String[] { "phoneNumber", "location", "info" }
                 ),
                 Arguments.of(
                         new AutoShopDto("MedinaMed","null", "089654123", "null", "null"),
                         new String[] { "emailShop", "location", "info" }
                 )
         );
     }
 }


