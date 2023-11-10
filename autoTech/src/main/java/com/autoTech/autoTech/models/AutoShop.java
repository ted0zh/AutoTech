package com.autoTech.autoTech.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "AutoShop")
public class AutoShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , updatable = false)
    private Long id;

    @Column(name = "name")
    private String shopName;

    @Column(name = "shop_email")
    private String emailShop;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "info")
    private String info;
    @ManyToMany
    @JoinTable(
            name = "shop_specialization",
            joinColumns=@JoinColumn(name="shop_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private Set<Specializations>specializations;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailShop() {
        return emailShop;
    }

    public void setEmailShop(String emailShop) {
        this.emailShop = emailShop;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AutoShop() {
    }

}
