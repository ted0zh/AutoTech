package com.autoTech.autoTech.repositories;

import com.autoTech.autoTech.data.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
