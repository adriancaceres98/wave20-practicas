package com.bootcamp.covid.service;

import com.bootcamp.covid.model.Sintoma;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SintomaService {

    private final List<Sintoma> sintomas;

    public SintomaService () {
        sintomas = new ArrayList<>();
    }

    public long addSintoma (Sintoma sintoma) {
        sintomas.add(sintoma);
        return sintoma.getCodigo();
    }

    public List<Sintoma> getAllSintomas() {
        return sintomas;
    }

    public Sintoma getSintoma (String nombre) {
        return sintomas.stream()
                .filter(s -> s.getNombre().equals(nombre))
                .findAny()
                .orElse(null);
    }
}
