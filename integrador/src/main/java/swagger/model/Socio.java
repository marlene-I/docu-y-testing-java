package swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import swagger.model.Persona;
import io.swagger.v3.oas.annotations.media.Schema;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Socio
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T01:15:12.905934647Z[GMT]")


public class Socio   {
  @JsonProperty("persona")
  private Persona persona = null;

  @JsonProperty("idSocio")
  private Integer idSocio = null;

  @JsonProperty("fechaIngreso")
  private LocalDate fechaIngreso = null;

  public Socio persona(Persona persona) {
    this.persona = persona;
    return this;
  }

  public Socio() { }

  public Socio(Persona p) {
		this.persona = p;
	}

  /**
   * Get persona
   * @return persona
   **/
  @Schema(description = "")
  
    @Valid
    public Persona getPersona() {
    return persona;
  }

  public void setPersona(Persona persona) {
    this.persona = persona;
  }

  public Socio idSocio(Integer idSocio) {
    this.idSocio = idSocio;
    return this;
  }

  /**
   * Identificador único del socio
   * @return idSocio
   **/
  @Schema(description = "Identificador único del socio")
  
    public Integer getIdSocio() {
    return idSocio;
  }

  public void setIdSocio(Integer idSocio) {
    this.idSocio = idSocio;
  }

  public Socio fechaIngreso(LocalDate fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
    return this;
  }

  /**
   * Fecha de ingreso del socio
   * @return fechaIngreso
   **/
  @Schema(description = "Fecha de ingreso del socio")
  
    @Valid
    public LocalDate getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(LocalDate fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Socio socio = (Socio) o;
    return Objects.equals(this.persona, socio.persona) &&
        Objects.equals(this.idSocio, socio.idSocio) &&
        Objects.equals(this.fechaIngreso, socio.fechaIngreso);
  }

  @Override
  public int hashCode() {
    return Objects.hash(persona, idSocio, fechaIngreso);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Socio {\n");
    
    sb.append("    persona: ").append(toIndentedString(persona)).append("\n");
    sb.append("    idSocio: ").append(toIndentedString(idSocio)).append("\n");
    sb.append("    fechaIngreso: ").append(toIndentedString(fechaIngreso)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  public String getNombreCompleto(){
    return this.getPersona().getApellido() + ", " + this.getPersona().getNombre();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
