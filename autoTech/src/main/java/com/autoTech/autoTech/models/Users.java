package com.autoTech.autoTech.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class Users {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName; // first_name

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_mail")
    private String userMail; // user_mail

    @Column(name = "user_number")
    private String userNumber; // user_number

    @OneToMany(mappedBy = "users")
    Set<Grades> ratings;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserMail() {return userMail;}

    public void setUserMail(String userMail) {this.userMail = userMail;}

    public String getUserNumber() {return userNumber;}

    public void setUserNumber(String userNumber) {this.userNumber = userNumber;}
}
