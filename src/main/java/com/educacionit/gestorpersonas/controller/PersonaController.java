package com.educacionit.gestorpersonas.controller;

import com.educacionit.gestorpersonas.model.Persona;
import com.educacionit.gestorpersonas.service.PersonaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/personas")
@Tag(name = "Personas", description = "Operaciones relacionadas con la gestión de personas")
@PreAuthorize("denyAll()")
public class PersonaController {

  private final PersonaService personaService;

  @Autowired
  public PersonaController(PersonaService personaService) {
    this.personaService = personaService;
  }

  @GetMapping()
  @Operation(summary = "Obtener todas las personas", description = "Devuelve una lista de todas las personas registradas.")
  @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente.")
  @PreAuthorize("permitAll()")
  public List<Persona> getPersonas() {
    return personaService.getAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Obtener una persona por ID", description = "Busca y retorna los datos de una persona por su ID único.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Persona encontrada."),
          @ApiResponse(responseCode = "404", description = "Persona no encontrada.")
  })
  @PreAuthorize("hasAuthority('READ')")
  public ResponseEntity<Persona> getPersona(
          @Parameter(description = "ID único de la persona", required = true) @PathVariable Long id) {
    try {
      return new ResponseEntity<>(personaService.getBy(id), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping()
  @Operation(summary = "Crear una nueva persona", description = "Agrega una nueva persona al sistema con los datos proporcionados.")
  @ApiResponses({
          @ApiResponse(responseCode = "201", description = "Persona creada exitosamente."),
          @ApiResponse(responseCode = "400", description = "Datos inválidos.")
  })
  @PreAuthorize("hasAuthority('CREATE')")
  public ResponseEntity<Persona> createPersona(
          @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Detalles de la nueva persona", required = true)
          @RequestBody Persona persona) {
    return new ResponseEntity<>(personaService.save(persona), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Actualizar datos de una persona", description = "Actualiza los datos de una persona existente por su ID.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Persona actualizada exitosamente."),
          @ApiResponse(responseCode = "404", description = "Persona no encontrada.")
  })
  @PreAuthorize("hasAuthority('UPDATE')")
  public ResponseEntity<Persona> updatePersona(
          @Parameter(description = "ID único de la persona a actualizar", required = true) @PathVariable Long id,
          @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos actualizados de la persona", required = true)
          @RequestBody Persona persona) {
    try {
      Persona personaEdit = personaService.edit(id, persona);
      return new ResponseEntity<>(personaService.save(personaEdit), HttpStatus.OK);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Eliminar una persona", description = "Elimina a una persona del sistema por su ID.")
  @ApiResponses({
          @ApiResponse(responseCode = "200", description = "Persona eliminada exitosamente."),
          @ApiResponse(responseCode = "404", description = "Persona no encontrada.")
  })
  @PreAuthorize("hasAuthority('DELETE')")
  public ResponseEntity<Void> deletePersona(
          @Parameter(description = "ID único de la persona a eliminar", required = true) @PathVariable Long id) {
    personaService.delete(id);
    return ResponseEntity.ok().build();
  }
}
