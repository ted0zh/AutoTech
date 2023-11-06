package com.autoTech.autoTech.repositories;
import com.autoTech.autoTech.models.AutoShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface AutoShopRepo extends JpaRepository<AutoShop,Long> {
    Optional<AutoShop> findAutoShopByShopName(String shopName);
//    @Query("SELECT a FROM AutoShop a WHERE a.serviceSpec =: serviceSpec")
//    List<AutoShop> filterShops(@Param("serviceSpec") String serviceSpec);

}
