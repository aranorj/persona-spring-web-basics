package com.educacionit.gestorpersonas.controller;

import com.educacionit.gestorpersonas.model.Persona;
import com.educacionit.gestorpersonas.service.PersonaService;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
public class PersonaController {

  private PersonaService personaService;

  public PersonaController(@Autowired PersonaService personaService) {
    this.personaService = personaService;
  }

  @GetMapping()
  public List<Persona> getPersonas() {
    return personaService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Persona> getPersona(@PathVariable Long id) {
    try {
      return new ResponseEntity<>(personaService.getBy(id), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/")
  public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
    return new ResponseEntity<>(personaService.save(persona), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona){
    try {
      Persona personaEdit = personaService.edit(id, persona);
      return new ResponseEntity<>(personaService.save(personaEdit), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePersona(@PathVariable Long id){
    personaService.delete(id);
    return ResponseEntity.ok().build();
  }



}
