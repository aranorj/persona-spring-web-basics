package com.educacionit.gestorpersonas.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Representa la dirección de una persona.")
public class Direccion {

  @Schema(description = "Nombre de la calle.", example = "Av. Siempre Viva")
  private String calle;

  @Schema(description = "Nombre de la ciudad.", example = "Springfield", required = true)
  private String ciudad;

  @Schema(description = "Código postal de la dirección.", example = "12345", required = true)
  private int codigoPostal;

  public Direccion(String calle, String ciudad, int codigoPostal) {
    this.calle = calle;
    this.ciudad = ciudad;
    this.codigoPostal = codigoPostal;
  }

  public String getCalle() {
    return calle;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  public String getCiudad() {
    return ciudad;
  }

  public void setCiudad(String ciudad) {
    this.ciudad = ciudad;
  }

  public int getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(int codigoPostal) {
    this.codigoPostal = codigoPostal;
  }
}
