package com.autoTech.autoTech.controllers;
import com.autoTech.autoTech.dto.UserDto;
import com.autoTech.autoTech.models.Users;
import com.autoTech.autoTech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("/fetch")
    public List<Users> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        Users savedInDb = userService.saveUser(userDto);
        return new ResponseEntity<>(savedInDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}