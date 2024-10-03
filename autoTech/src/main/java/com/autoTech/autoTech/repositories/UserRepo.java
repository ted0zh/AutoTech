package com.autoTech.autoTech.repositories;

import com.autoTech.autoTech.data.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository <Users,Long> {

    Optional<Users> findUserByUserMail(String userMail);

    @Query("SELECT u FROM Users u")
    List<Users> findAllUsers();
}
