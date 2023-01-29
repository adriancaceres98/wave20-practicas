package com.mercadolibre.SegurosAutos.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Siniestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy/MM/dd")
    private LocalDate fechaSiniestro;

    private double perdidaEconomica;

    @ManyToOne
    private Vehiculo vehiculo;

}
