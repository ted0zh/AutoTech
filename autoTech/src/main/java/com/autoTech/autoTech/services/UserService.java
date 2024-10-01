package com.autoTech.autoTech.services;

import com.autoTech.autoTech.data.mapper.UserMapper;
import com.autoTech.autoTech.data.dto.UserDto;
import com.autoTech.autoTech.data.models.Users;
import com.autoTech.autoTech.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserMapper userMapper, UserRepo userRepo) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
    }
    public List<Users> findAllUsers(){
        return userRepo.findAll();
    }

    public Users saveUser(UserDto userDto) {
        Optional<Users> dbObject = userRepo.findUserByUserMail(userDto.userMail());
        Long id;
        if(dbObject.isPresent()) {
            id = dbObject.get().getId();
        } else {
            id = null;
        }
        Users user = userMapper.convertDtoToEntity(userDto, id);
        return userRepo.saveAndFlush(user);
    }

    public void deleteUser(Long id) { this.userRepo.deleteById(id);}
}
