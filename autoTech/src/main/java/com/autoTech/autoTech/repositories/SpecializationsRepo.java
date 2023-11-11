package com.autoTech.autoTech.repositories;

import com.autoTech.autoTech.models.Specializations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationsRepo extends JpaRepository<Specializations,Long> {
    Specializations findSpecializationBySpecializationName(String specializationName);
}
