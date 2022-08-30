package com.example.springboot_demo.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Table(name = "phone")
@Getter
@Setter
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @NotNull
    private String name;

    @NotNull
    private double price;

    private String color;
    private String description;
    private String imageUrl;

    @ManyToOne
    private Brand brand;

    public Phone() {

    }

    public Phone(String name, double price, String color, String description, String imageUrl, Brand brand) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.description = description;
        this.imageUrl = imageUrl;
        this.brand = brand;
    }

}
