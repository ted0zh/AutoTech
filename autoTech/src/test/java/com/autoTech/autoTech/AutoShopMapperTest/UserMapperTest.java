package com.autoTech.autoTech.AutoShopMapperTest;


import com.autoTech.autoTech.Mapper.UserMapper;
import com.autoTech.autoTech.dto.UserDto;
import com.autoTech.autoTech.models.Users;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testConvertDtoToEntity() {

        UserDto dto = new UserDto("Penko", "Penev", "ppenev@poshta.bg", "12312312312");


        Users user = mapper.convertDtoToEntity(dto, 1L);

        assertEquals("Penko", user.getFirstName());
        assertEquals("Penev", user.getLastName());
        assertEquals("ppenev@poshta.bg", user.getUserMail());
        assertEquals("12312312312", user.getUserNumber());
        assertEquals(1L, user.getId());
    }
}

