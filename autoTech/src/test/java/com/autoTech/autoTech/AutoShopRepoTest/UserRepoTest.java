package com.autoTech.autoTech.AutoShopRepoTest;

import com.autoTech.autoTech.models.Users;
import com.autoTech.autoTech.repositories.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("DELETE FROM Users").executeUpdate();
    }

    @Test
    @Rollback(false)
    public void whenFindByUserMail_thenReturnUser() {

        Users user = new Users();
        user.setUserMail("ppenev@poshta.bg");
        entityManager.persist(user);
        entityManager.flush();
        Optional<Users> found = userRepo.findUserByUserMail("ppenev@poshta.bg");
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getUserMail()).isEqualTo("ppenev@poshta.bg");
    }

    @Test
    @Rollback(false)
    public void whenFindAll_thenReturnAllUsers() {

        Users user1 = new Users();
        user1.setUserMail("ppenev@poshta.bg");
        entityManager.persist(user1);
        Users user2 = new Users();
        user2.setUserMail("tedochuka123@poshta.bg");
        entityManager.persist(user2);
        entityManager.flush();

        List<Users> users = userRepo.findAllUsers();
        assertThat(users).hasSize(2);
        assertThat(users).extracting(Users::getUserMail).containsOnly("ppenev@poshta.bg", "tedochuka123@poshta.bg");
    }
}
