package com.example.crud_joyeria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JoyaDto {

    private Long noIdentificatorio;

    private String nombre;

    private String material;

    private Double peso;

    private String particularidad;

    private Boolean poseePiedra;

    private Boolean ventaONo;
}
