package com.autoTech.autoTech.models;

import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_grade")
    private Double serviceGrade; // service_grade

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Double getServiceGrade() {return serviceGrade;}

    public void setServiceGrade(Double serviceGrade) {this.serviceGrade = serviceGrade;}

    public Grades(Long id, Double serviceGrade) {
        this.id = id;
        this.serviceGrade = serviceGrade;
    }
}
