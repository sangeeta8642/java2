package com.crud.users.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String industry;


    @OneToMany(mappedBy = "company")
    private List<Users> users;


    // Constructors
    public Company() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getIndustry() {
        return industry;
    }

    public Company(String name, String location, String industry) {

        this.name = name;
        this.location = location;
        this.industry = industry;
    }


}
