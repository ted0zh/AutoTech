package com.autoTech.autoTech.ControllerTest;

import com.autoTech.autoTech.controllers.UserController;
import com.autoTech.autoTech.dto.UserDto;
import com.autoTech.autoTech.models.Users;
import com.autoTech.autoTech.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    // Test for findAllUsers
    @Test
    public void testFindAllUsers() {
        // Given
        Users user1 = new Users(); // Assume Users is a properly defined entity
        Users user2 = new Users();
        List<Users> expectedUsers = Arrays.asList(user1, user2);
        given(userService.findAllUsers()).willReturn(expectedUsers);

        // When
        List<Users> actualUsers = userController.findAllUsers();

        // Then
        assertSame(expectedUsers, actualUsers);
        verify(userService).findAllUsers();
    }

    // Test for saveUser
    @Test
    public void testSaveUser() {
        // Given
        UserDto userDto = new UserDto("Ivan","Vanov","ivvano@mail.bg","12321314"); // Assume UserDto is a properly defined DTO
        Users savedUser = new Users();
        given(userService.saveUser(any(UserDto.class))).willReturn(savedUser);

        // When
        ResponseEntity<?> response = userController.saveUser(userDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(savedUser, response.getBody());
        verify(userService).saveUser(any(UserDto.class));
    }

    // Test for deleteUser
    @Test
    public void testDeleteUser() {
        // Given
        Long userId = 1L;
        willDoNothing().given(userService).deleteUser(userId);

        // When
        ResponseEntity<?> response = userController.deleteUser(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).deleteUser(userId);
    }
}
