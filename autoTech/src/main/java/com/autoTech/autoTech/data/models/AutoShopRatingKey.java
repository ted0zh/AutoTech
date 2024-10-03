package com.autoTech.autoTech.data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class AutoShopRatingKey implements Serializable {

    @Column(name = "users_id")
    Long usersId;

    @Column(name = "autoShop_id")
    Long autoShopId;

}
