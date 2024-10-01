package com.autoTech.autoTech.AutoShopRepoTest;
import com.autoTech.autoTech.data.models.AutoShop;
import com.autoTech.autoTech.repositories.AutoShopRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;


@DataJpaTest

public class AutoShopRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AutoShopRepo autoShopRepo;

    @Test
    void testFindAutoShopsByName() {

        AutoShop autoShop = new AutoShop();
        autoShop.setShopName("NSN");
        entityManager.persist(autoShop);
        entityManager.flush();
        Optional<AutoShop> result = autoShopRepo.findAutoShopByShopName("NSN");
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(shop -> assertThat(shop.getShopName()).isEqualTo("NSN"));
    }
}
