package com.autoTech.autoTech.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "specializations")
@Data
public class Specializations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "specialization")
    private String specialization;
    // service_specialization


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public Specializations() {
    }


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}





}
