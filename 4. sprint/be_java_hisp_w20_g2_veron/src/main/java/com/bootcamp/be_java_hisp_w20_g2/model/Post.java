package com.bootcamp.be_java_hisp_w20_g2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional;


@AllArgsConstructor
@Data
public class Post {
    private Integer id;
    private LocalDate date;
    private Product product;
    private Category category;
    private double price;
    private Optional<Boolean> hasPromo;
    private Optional<Double> discount;


    public Post(LocalDate date, Product product, Category category, double price) {
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = Optional.of(false);
        this.discount = Optional.empty();
    }

    public Post(LocalDate date, Product product, Category category, double price,boolean hasPromo,double discount) {
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = Optional.of(hasPromo);
        this.discount = Optional.of(discount);
    }
}
