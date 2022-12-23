package com.bootcamp.ejercicio.dto;

public class PersonajeDto {

    private String nombre;
    private String empresa;

    public PersonajeDto() {
    }

    public PersonajeDto(String nombre, String empresa) {
        this.nombre = nombre;
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
