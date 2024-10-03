package com.autoTech.autoTech.ControllerTest;

import com.autoTech.autoTech.controllers.AutoShopController;
import com.autoTech.autoTech.data.dto.AutoShopDto;
import com.autoTech.autoTech.data.models.AutoShop;
import com.autoTech.autoTech.services.AutoShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
@ExtendWith(MockitoExtension.class)
public class AutoShopControllerTest {

    @Mock
    private AutoShopService autoShopService;

    @InjectMocks
    private AutoShopController autoShopController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(autoShopController).build();
    }

    @Test
    public void fetchAutoShopsShouldReturnAutoShops() throws Exception {
        List<AutoShop> autoShops = Collections.singletonList(new AutoShop(){{
            setId(1L);
            setShopName("NSN");
            setEmailShop("nsn.tuning@abv.bg");
            setPhoneNumber("0888555555");
            setLocation("Sofia");
            setInfo("Newbies");
        }});
        when(autoShopService.getAllShops()).thenReturn(autoShops);

        mockMvc.perform(get("/shops/fetch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].shopName", is(autoShops.get(0).getShopName())));

        verify(autoShopService, times(1)).getAllShops();
    }

    @Test
    public void saveAutoShopShouldReturnSavedAutoShop() throws Exception {
        AutoShopDto autoShopDto = new AutoShopDto("G34", "G34@example.com",
                "1234567890", "Sofia", "Newbies");
        AutoShop autoShop = new AutoShop(){{
            setId(3L);
            setShopName("G34");
            setEmailShop("G34@example.com");
            setPhoneNumber("1234567890");
            setLocation("Sofia");
            setInfo("Newbies");
        }};
        when(autoShopService.saveShop(any(AutoShopDto.class))).thenReturn(autoShop);


        mockMvc.perform(post("/shops/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(autoShopDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.shopName", is(autoShop.getShopName())));

        verify(autoShopService, times(1)).saveShop(any(AutoShopDto.class));
    }

    @Test
    public void deleteAutoShopShouldReturnStatusOk() throws Exception {
        Long shopId = 1L;
        doNothing().when(autoShopService).deleteAutoShop(shopId);
        mockMvc.perform(delete("/shops/delete/{id}", shopId))
                .andExpect(status().isOk());

        verify(autoShopService, times(1)).deleteAutoShop(shopId);
    }

    @Test
    public void filterAutoShopsShouldReturnAutoShop() throws Exception {
        String shopName = "G34";
        AutoShop autoShop = new AutoShop(){{
                setId(3L);
                setShopName("G34");
                setEmailShop("G34@example.com");
                setPhoneNumber("1234567890");
                setLocation("Sofia");
                setInfo("Newbies");
        }};
        when(autoShopService.filterAutoShops(shopName)).thenReturn(Optional.of(autoShop));

        mockMvc.perform(get("/shops/filter/{name}", shopName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shopName", is(shopName)));

        verify(autoShopService, times(1)).filterAutoShops(shopName);
    }

    @Test
    public void fetchAutoShopsPageableShouldReturnPage() throws Exception {
        // Arrange
        int currentPage = 1;
        int perPage = 2;
        Pageable pageable = PageRequest.of(currentPage - 1, perPage);
        List<AutoShop> autoShopList = Arrays.asList(
                new AutoShop(){{
                    setId(1L);
                    setShopName("NSN");
                    setEmailShop("nsn.tuning@abv.bg");
                    setPhoneNumber("0888555555");
                    setLocation("Sofia");
                    setInfo("Newbies");
                }},
                new AutoShop(){{
                    setId(2L);
                    setShopName("Adler");
                    setEmailShop("adler.tuning@abv.bg");
                    setPhoneNumber("0887578555");
                    setLocation("Sofia");
                    setInfo("Veterans");
                }}
        );
        Page<AutoShop> autoShopPage = new PageImpl<>(autoShopList, pageable, autoShopList.size());
        when(autoShopService.getAllAutoShops(pageable)).thenReturn(autoShopPage);

        mockMvc.perform(get("/shops/page/shops")
                        .param("currentPage", String.valueOf(currentPage))
                        .param("perPage", String.valueOf(perPage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.auto-shops", hasSize(perPage)))
                .andExpect(jsonPath("$.totalPages", is(autoShopPage.getTotalPages())))
                .andExpect(jsonPath("$.totalElements", is((int) autoShopPage.getTotalElements())));

        verify(autoShopService, times(1)).getAllAutoShops(pageable);
    }
}
