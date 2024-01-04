package com.autoTech.autoTech.services;

import com.autoTech.autoTech.Mapper.UserMapper;
import com.autoTech.autoTech.dto.UserDto;
import com.autoTech.autoTech.models.AutoShop;
import com.autoTech.autoTech.models.Users;
import com.autoTech.autoTech.repositories.UserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
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
            log.info("Updating user with id {}", id);
        } else {
            id = null;
            log.info("Added new user");
        }
        Users user = userMapper.convertDtoToEntity(userDto, id);
        return userRepo.saveAndFlush(user);
    }

    public void deleteUser(Long id) { this.userRepo.deleteById(id);}
}
