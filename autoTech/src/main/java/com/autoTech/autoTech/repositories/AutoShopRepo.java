package com.autoTech.autoTech.repositories;
import com.autoTech.autoTech.data.models.AutoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface AutoShopRepo extends JpaRepository<AutoShop,Long> {
    Optional<AutoShop> findAutoShopByShopName(String shopName);
}
