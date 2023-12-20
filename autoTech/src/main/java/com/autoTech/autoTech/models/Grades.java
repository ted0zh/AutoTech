package com.autoTech.autoTech.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "grades")
@Data
public class Grades {
    @EmbeddedId
    AutoShopRatingKey id;

    @ManyToOne
    @MapsId("usersId")
    @JoinColumn(name = "users_id")
    Users users;

    @ManyToOne
    @MapsId("autoShopId")
    @JoinColumn(name = "autoShop_id")
    AutoShop autoShop;

    @Column(name = "service_grade")
    private Double serviceGrade; // service_grade
    public AutoShopRatingKey getId() {
        return id;
    }
    public void setId(AutoShopRatingKey id) {
        this.id = id;
    }
    public Double getServiceGrade() {return serviceGrade;}
    public void setServiceGrade(Double serviceGrade) {this.serviceGrade = serviceGrade;}
}