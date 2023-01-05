package com.socialmeli.be_java_hisp_w20_g8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDTO {
    private int user_id;
    private int post_id;
    private LocalDate date;
    private ProductDTO product;
    private int category;
    private double price;
    private boolean hasPromo;
    private double discount;

    public PostDTO(int user_id, LocalDate date, ProductDTO product, int category, double price) {
        this.user_id = user_id;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = false;
        this.discount = 0;
    }

    public PostDTO(int user_id, LocalDate date, ProductDTO product, int category, double price, boolean hasPromo, double discount) {
        this.user_id = user_id;
        this.date = date;
        this.product = product;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

}
