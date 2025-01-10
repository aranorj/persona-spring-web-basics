package com.educacionit.gestorpersonas.repository;

import com.educacionit.gestorpersonas.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    List<Persona> findAll();

    Optional<Persona> findBy(Long id);

    Persona save(Persona persona);

    void delete(Long id);
}
