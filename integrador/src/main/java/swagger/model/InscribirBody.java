package swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import swagger.model.Actividad;
import swagger.model.Socio;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InscribirBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T01:15:12.905934647Z[GMT]")

public class InscribirBody {
  @JsonProperty("socio")
  private Socio socio = null;

  @JsonProperty("actividad")
  private Actividad actividad = null;

  public InscribirBody socio(Socio socio) {
    this.socio = socio;
    return this;
  }

  /**
   * Get socio
   * 
   * @return socio
   **/
  @Schema(description = "Socio a inscribir")

  @Valid
  public Socio getSocio() {
    return socio;
  }

  public void setSocio(Socio socio) {
    this.socio = socio;
  }

  public InscribirBody actividad(Actividad actividad) {
    this.actividad = actividad;
    return this;
  }

  /**
   * Get actividad
   * 
   * @return actividad
   **/
  @Schema(description = "Actividad en la cual inscribir al Socio")

  @Valid
  public Actividad getActividad() {
    return actividad;
  }

  public void setActividad(Actividad actividad) {
    this.actividad = actividad;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InscribirBody inscribirBody = (InscribirBody) o;
    return Objects.equals(this.socio, inscribirBody.socio) &&
        Objects.equals(this.actividad, inscribirBody.actividad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(socio, actividad);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InscribirBody {\n");

    sb.append("    socio: ").append(toIndentedString(socio)).append("\n");
    sb.append("    actividad: ").append(toIndentedString(actividad)).append("\n");
    sb.append("}");
    return sb.toString();
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
