package com.bootcamp.be_java_hisp_w20_g1.dto.response;

import com.bootcamp.be_java_hisp_w20_g1.dto.response.interfaces.BasePostDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PromoPostCountDto implements BasePostDto {
    private int userId;
    private String userName;
    private int promoProductsCount;
}
