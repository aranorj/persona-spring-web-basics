package com.educacionit.gestorpersonas.service;

import com.educacionit.gestorpersonas.model.Persona;
import com.educacionit.gestorpersonas.repository.PersonaRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

  private PersonaRepository personaRepository;

  public PersonaServiceImpl(@Autowired PersonaRepository personaRepository) {
    this.personaRepository = personaRepository;
  }

  @Override
  public Persona getBy(Long id) {
    return personaRepository.findBy(id).orElseThrow(NoSuchElementException::new);
  }

  @Override
  public List<Persona> getAll(){
    return personaRepository.findAll();
  }

  @Override
  public Persona save(Persona persona){
    return personaRepository.save(persona);
  }

  @Override
  public void delete(Long id){
    personaRepository.delete(id);
  }

  @Override
  public Persona edit(Long id, Persona persona) {
    Persona personaEdit = getBy(id);
    personaEdit.setNombre(persona.getNombre());
    personaEdit.setEdad(persona.getEdad());
    personaEdit.setDireccion(persona.getDireccion());
    personaEdit.setTelefono(persona.getTelefono());
    personaEdit.setEmail(persona.getEmail());
    //personaRepository.save(personaEdit);
    return personaEdit;
  }
}
