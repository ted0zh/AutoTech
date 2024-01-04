package com.autoTech.autoTech.ControllerTest;

import com.autoTech.autoTech.controllers.UserController;
import com.autoTech.autoTech.dto.UserDto;
import com.autoTech.autoTech.models.Users;
import com.autoTech.autoTech.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findAllUsers_ShouldReturnListOfUsers() throws Exception {
        // Arrange
        List<Users> users = Arrays.asList(
                new Users(){
                    {
                        setId(3L);
                        setFirstName("Penko");
                        setLastName("Penev");
                        setUserMail("ppenev@poshta.bg");
                        setUserNumber("12312312312"); }});
                        new Users(){
                            {
                                setId(3L);
                                setFirstName("tedo");
                                setLastName("veroto");
                                setUserMail("tedochuka123@poshta.bg");
                                setUserNumber("32132132132"); }};

        when(userService.findAllUsers()).thenReturn(users);


        mockMvc.perform(get("/users/fetch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id", is(users.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].firstName", is(users.get(0).getFirstName())));

        verify(userService, times(1)).findAllUsers();
    }

    @Test
    public void saveUser_ShouldReturnSavedUser() throws Exception {

        UserDto userDto = new UserDto("John", "Doe",
                "john.doe@example.com", "1234567890");
        Users savedUser = new Users(){
            {
                setId(3L);
                setFirstName("John");
                setLastName("Doe");
                setUserMail("john.doe@example.com");
                setUserNumber("1234567890"); }};
        when(userService.saveUser(any(UserDto.class))).thenReturn(savedUser);


        mockMvc.perform(post("/users/saveUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(savedUser.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(savedUser.getFirstName())));

        verify(userService, times(1)).saveUser(any(UserDto.class));
    }

    @Test
    public void deleteUser_ShouldReturnStatusOk() throws Exception {

        Long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        // Act & Assert
        mockMvc.perform(delete("/users/delete/{id}", userId))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(userId);
    }
}
