package com.bootcamp.be_java_hisp_w20_g4.dto.response.publication;

import com.bootcamp.be_java_hisp_w20_g4.dto.response.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromoPublicationDTO {
    private LocalDate date;
    private ProductDTO product;
    private Integer category;
    private double price;
    private boolean has_promo;
    private double discount;
}
