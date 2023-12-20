package com.autoTech.autoTech.ControllerTest;


import com.autoTech.autoTech.dto.AutoShopDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.services.AutoShopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.lang.reflect.Array.get;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
public class AutoShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutoShopService autoShopService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void fetchAutoShopsShouldReturnAutoShops() throws Exception {
        List<AutoShop> autoShops = Arrays.asList(new AutoShop(/* Initialize fields */), new AutoShop(/* Initialize fields */));
        when(autoShopService.getAllShops()).thenReturn(autoShops);

//        mockMvc.perform(get("/shops/fetch"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(autoShops.size())))
//                .andExpect(jsonPath("$[0].name", is(autoShops.get(0).getShopName()))); // Assuming there is a getName method

        verify(autoShopService).getAllShops();
    }

    @Test
    public void saveAutoShopShouldReturnSavedAutoShop() throws Exception {
        AutoShopDto autoShopDto = new AutoShopDto("Alien","alien@abv.bg","213415","Sofia","newbies");
        AutoShop autoShop = new AutoShop(/* Initialize fields */);
        when(autoShopService.saveShop(any(AutoShopDto.class))).thenReturn(autoShop);

//        mockMvc.perform(post("/shops/save")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(/* Convert autoShopDto to JSON */))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name", is(autoShop.getShopName()))); // Assuming there is a getName method

        verify(autoShopService).saveShop(any(AutoShopDto.class));
    }

    @Test
    public void deleteAutoShopShouldReturnStatusOk() throws Exception {
        Long shopId = 1L;
        doNothing().when(autoShopService).deleteAutoShop(shopId);

        mockMvc.perform(delete("/shops/delete/{id}", shopId))
                .andExpect(status().isOk());

        verify(autoShopService).deleteAutoShop(shopId);
    }

    @Test
    public void filterAutoShopsShouldReturnAutoShop() throws Exception {
        String shopName = "AutoShopName";
        AutoShop autoShop = new AutoShop(/* Initialize fields */);
        when(autoShopService.filterAutoShops(shopName)).thenReturn(Optional.of(autoShop));

//        mockMvc.perform(get("/shops/filter/{name}", shopName))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is(autoShop.getShopName()))); // Assuming there is a getName method

        verify(autoShopService).filterAutoShops(shopName);
    }

    @Test
    public void fetchAutoShopsPageableShouldReturnPage() throws Exception {
        Pageable pageable = PageRequest.of(0, 2);
        List<AutoShop> autoShopList = Arrays.asList(new AutoShop(/* Initialize fields */), new AutoShop(/* Initialize fields */));
        Page<AutoShop> autoShopPage = new PageImpl<>(autoShopList, pageable, autoShopList.size());
        when(autoShopService.getAllAutoShops(any(Pageable.class))).thenReturn(autoShopPage);

//        mockMvc.perform(MockMvcRequestBuilders.get("/shops/page/shops")
//                        .param("currentPage", "1")
//                        .param("perPage", "2"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.totalPages", is(autoShopPage.getTotalPages())))
//                .andExpect(jsonPath("$.totalElements", is((int) autoShopPage.getTotalElements())))
//                .andExpect(jsonPath("$.auto-shops", hasSize(autoShopList.size())));

        verify(autoShopService).getAllAutoShops(any(Pageable.class));
    }

}
