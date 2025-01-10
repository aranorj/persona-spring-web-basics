package com.educacionit.gestorpersonas.repository;

import com.educacionit.gestorpersonas.model.Persona;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class PersonaRepositoryImpl implements PersonaRepository {

  private List<Persona> personas = new ArrayList<>();

  @Override
  public List<Persona> findAll() {
    return personas;
  }

  @Override
  public Optional<Persona> findBy(Long id) {
    return personas.stream().filter(p -> p.getId() == id).findFirst();
  }

  @Override
  public Persona save(Persona persona) {
    personas.add(persona);
    return persona;
  }

  @Override
  public void delete(Long id) {
    personas.removeIf(p -> p.getId() == id);
  }

}
