package com.educacionit.gestorpersonas.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa una persona en el sistema.")
public class Persona {

  @Schema(description = "ID único de la persona. Este campo es autogenerado.", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;

  @Schema(description = "Nombre de la persona.", example = "Juan Pérez", required = true)
  private String nombre;

  @Schema(description = "Edad de la persona.", example = "30", required = true)
  private int edad;

  @Schema(description = "Dirección asociada a la persona.")
  private Direccion direccion;

  @Schema(description = "Número de teléfono de la persona.", example = "+5491123456789")
  private String telefono;

  @Schema(description = "Correo electrónico de la persona.", example = "juan.perez@example.com")
  private String email;

  private static Long idCounter = 0L;

  public Persona(String nombre, int edad, Direccion direccion, String telefono, String email) {
    this.id = ++idCounter;
    this.nombre = nombre;
    this.edad = edad;
    this.direccion = direccion;
    this.telefono = telefono;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }

  public Direccion getDireccion() {
    return direccion;
  }

  public void setDireccion(Direccion direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
