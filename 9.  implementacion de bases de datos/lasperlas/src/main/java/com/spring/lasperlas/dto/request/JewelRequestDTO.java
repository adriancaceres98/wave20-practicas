package com.spring.lasperlas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JewelRequestDTO {
    private String name;
    private String material;
    private Double weight;
    private String particularity;
    private Boolean haveRock;
    private Boolean forSale;
}
