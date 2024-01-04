package com.autoTech.autoTech.AutoShopRepoTest;

import com.autoTech.autoTech.models.Specializations;
import com.autoTech.autoTech.repositories.SpecializationsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SpecializationsRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpecializationsRepo specializationsRepo;

    @Test
    public void whenFindByName_thenReturnSpecialization() {
        Specializations specialization = new Specializations();
        specialization.setSpecialization("tyres");
        entityManager.persist(specialization);
        entityManager.flush();
        Optional<Specializations> found = specializationsRepo.findSpecializationBySpecialization("tyres");
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getSpecialization()).isEqualTo("tyres");
    }

    @Test
    public void whenDeleteByName_thenNotFound() {
        Specializations specialization = new Specializations();
        specialization.setSpecialization("tyres");
        entityManager.persist(specialization);
        entityManager.flush();
        specializationsRepo.deleteSpecializationBySpecialization("tyres");
        Optional<Specializations> deleted = specializationsRepo.findSpecializationBySpecialization("tyres");
        assertThat(deleted.isPresent()).isFalse();
    }
}
