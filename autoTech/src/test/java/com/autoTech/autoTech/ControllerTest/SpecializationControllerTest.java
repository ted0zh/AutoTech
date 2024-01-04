package com.autoTech.autoTech.ControllerTest;

import com.autoTech.autoTech.controllers.ShopSpecializationController;
import com.autoTech.autoTech.dto.SpecializationsDto;
import com.autoTech.autoTech.models.Specializations;
import com.autoTech.autoTech.services.SpecializationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ShopSpecializationController.class)
public class SpecializationControllerTest {
            @Autowired
        private MockMvc mockMvc;

        @MockBean
        private SpecializationService specializationService;

        @InjectMocks
        private ShopSpecializationController shopSpecializationController;

        @Before
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }

        @Test
        public void getAllSpecializationsTest() throws Exception {
            List<Specializations> specializationsList = Arrays.asList(
                    new Specializations(), // Assuming constructor accepts the specialization name
                    new Specializations()
            );

            when(specializationService.getAllSpecializations()).thenReturn(specializationsList);

            mockMvc.perform(get("/specialization/fetch"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].specialization", is("tyres")))
                    .andExpect(jsonPath("$[1].specialization", is("engine")));

            verify(specializationService).getAllSpecializations();
        }

        @Test
        public void saveSpecializationTest() throws Exception {
            SpecializationsDto dto = new SpecializationsDto("brakes");
            Specializations savedSpecialization = new Specializations();
            savedSpecialization.setId(3L); // Assuming the ID is set after saving

            when(specializationService.saveSpecialization(any()))
                    .thenReturn(savedSpecialization);

            mockMvc.perform(post("/specialization/save")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"specialization\":\"brakes\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(3)))
                    .andExpect(jsonPath("$.specialization", is("brakes")));

            verify(specializationService).saveSpecialization(any());
        }

        @Test
        public void deleteSpecializationTest() throws Exception {
            String specialization = "brakes";

            doNothing().when(specializationService).deleteBySpecialization(specialization);

            mockMvc.perform(delete("/specialization/delete/{specialization}", specialization))
                    .andExpect(status().isOk());

            verify(specializationService).deleteBySpecialization(specialization);
        }

        @Test
        public void filterSpecializationTypeTest() throws Exception {
            String specialization = "brakes";
            Specializations specializations = new Specializations();
            specializations.setId(3L);

            when(specializationService.filterSpecializations(specialization)).thenReturn(Optional.of(specializations));

            mockMvc.perform(get("/specialization/filter/{specialization}", specialization))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(3)))
                    .andExpect(jsonPath("$.specialization", is(specialization)));

            verify(specializationService).filterSpecializations(specialization);
        }
}
