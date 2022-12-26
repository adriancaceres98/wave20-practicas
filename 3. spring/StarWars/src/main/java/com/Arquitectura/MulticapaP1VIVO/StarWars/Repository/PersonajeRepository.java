package com.Arquitectura.MulticapaP1VIVO.StarWars.Repository;

import com.Arquitectura.MulticapaP1VIVO.StarWars.Dto.PersonajeDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class PersonajeRepository {
    List<PersonajeRepository> personajeRepositories;

    private List<PersonajeDto> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:StarWars.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PersonajeDto>> typeRef = new TypeReference<>() {};
        List<PersonajeDto> characters = null;
        try {
            characters = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return characters;
    }
}
