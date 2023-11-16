package com.autoTech.autoTech.repositories;

import com.autoTech.autoTech.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository <Users,Long> {
    Optional<Users> findUserByUserMail(String userMail);
}
