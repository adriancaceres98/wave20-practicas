package com.bootcamp.be_java_hisp_w20_g7.dto;

import com.bootcamp.be_java_hisp_w20_g7.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {

    private int userId;
    private int postId;
    private LocalDate date;
    private ProductDto product;
    private int category;
    private double price;

}
