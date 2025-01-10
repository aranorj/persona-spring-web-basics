package com.educacionit.gestorpersonas.service;

import com.educacionit.gestorpersonas.model.Persona;

import java.util.List;

public interface PersonaService {
    Persona getBy(Long id);

    List<Persona> getAll();

    Persona save(Persona persona);

    void delete(Long id);

    Persona edit(Long id, Persona persona);
}
