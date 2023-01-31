package com.meli.obtenerdiploma.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter @Setter
public class StudentDTO {

    @NotEmpty(message = "El nombre del estudiante no puede estar vacío")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del estudiante debe comenzar con mayúscula.")
    @Size(min = 0, max = 50, message = "La longitud del nombre no puede superar los 50 caracteres.")
    private String studentName;
    String message;
    Double averageScore;
    @Valid
    @NotEmpty(message = "La lista no puede ser vacia")
    private List<SubjectDTO> subjects;
}
