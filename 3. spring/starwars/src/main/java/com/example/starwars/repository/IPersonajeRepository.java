package com.example.starwars.repository;

import com.example.starwars.entity.Personaje;

import java.util.List;

public interface IPersonajeRepository {
     List<Personaje> listaPersonajes();
     List<Personaje> personajeXNombre(String nombre);
}
