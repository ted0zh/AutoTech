package com.autoTech.autoTech.ControllerTest;

import com.autoTech.autoTech.controllers.ShopSpecializationController;
import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.Specializations;
import com.autoTech.autoTech.services.SpecializationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class SpecializationControllerTest {

    @Mock
    private SpecializationService specializationService;

    @InjectMocks
    private ShopSpecializationController shopSpecializationController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(shopSpecializationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllSpecializations_ShouldReturnList() throws Exception {
        List<Specializations> specializations = Arrays.asList(
                new Specializations(){{
                    setId(4L);
                    setSpecialization("tyres");
                }},
                new Specializations()
                {{
                    setId(7L);
                    setSpecialization("engine");
                }}
        );

        given(specializationService.getAllSpecializations()).willReturn(specializations);

        mockMvc.perform(get("/specialization/fetch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(specializations.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].specialization", is(specializations.get(0).getSpecialization())));

        verify(specializationService, times(1)).getAllSpecializations();
    }

    @Test
    public void saveSpecialization_ShouldReturnCreatedSpecialization() throws Exception {
        SpecializationsDto dto = new SpecializationsDto("brakes");
        Specializations savedSpecialization = new Specializations() {{
            setId(1L);
            setSpecialization("brakes");
        }};

        given(specializationService.saveSpecialization(any(SpecializationsDto.class))).willReturn(savedSpecialization);

        mockMvc.perform(post("/specialization/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(savedSpecialization.getId().intValue())))
                .andExpect(jsonPath("$.specialization", is(savedSpecialization.getSpecialization())));

        verify(specializationService, times(1)).saveSpecialization(any(SpecializationsDto.class));
    }

    @Test
    public void deleteSpecialization_ShouldReturnOkStatus() throws Exception {
        String specialization = "tyres";

        doNothing().when(specializationService).deleteBySpecialization(specialization);

        mockMvc.perform(delete("/specialization/delete/{specialization}", specialization))
                .andExpect(status().isOk());

        verify(specializationService, times(1)).deleteBySpecialization(specialization);
    }

    @Test
    public void filterSpecializationType_ShouldReturnSpecialization() throws Exception {
        String specialization = "engine";
        Specializations specializations = new Specializations(){{
            setId(7L);
            setSpecialization("engine");
        }};

        given(specializationService.filterSpecializations(specialization)).willReturn(Optional.of(specializations));

        mockMvc.perform(get("/specialization/filter/{specialization}", specialization))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(specializations.getId().intValue())))
                .andExpect(jsonPath("$.specialization", is(specializations.getSpecialization())));

        verify(specializationService, times(1)).filterSpecializations(specialization);
    }
}
