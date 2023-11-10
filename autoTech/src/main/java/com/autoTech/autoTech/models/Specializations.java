package com.autoTech.autoTech.models;

import jakarta.persistence.*;

@Entity
@Table(name = "specializations")

public class Specializations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialization")
    private String specialization; // service_specialization


    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getServiceSpecializations() {return specialization;}

    public void setServiceSpecializations(String serviceSpecializations) {this.specialization = specialization;}



}
