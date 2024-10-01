package com.autoTech.autoTech.repositories;


import com.autoTech.autoTech.data.models.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecializationsRepo extends JpaRepository<Specializations,Long> {
    Optional<Specializations> findSpecializationBySpecialization(String specialization);

    void deleteSpecializationBySpecialization(String specialization);
}
