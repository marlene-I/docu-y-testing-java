package swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Persona
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T01:15:12.905934647Z[GMT]")


public class Persona   {
  @JsonProperty("nombre")
  private String nombre = null;

  @JsonProperty("apellido")
  private String apellido = null;

  @JsonProperty("dni")
  private String dni = null;

  @JsonProperty("edad")
  private Integer edad = null;

  public Persona(String nombre, String apellido, String dni, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
	}

  public Persona nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Nombre de la persona
   * @return nombre
   **/
  @Schema(description = "Nombre de la persona")
  
    public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Persona apellido(String apellido) {
    this.apellido = apellido;
    return this;
  }

  /**
   * Apellido de la persona
   * @return apellido
   **/
  @Schema(description = "Apellido de la persona")
  
    public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public Persona dni(String dni) {
    this.dni = dni;
    return this;
  }

  /**
   * DNI (Documento Nacional de Identidad) de la persona.
   * @return dni
   **/
  @Schema(description = "DNI (Documento Nacional de Identidad) de la persona.")
  
    public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public Persona edad(Integer edad) {
    this.edad = edad;
    return this;
  }

  /**
   * Edad de la persona
   * @return edad
   **/
  @Schema(description = "Edad de la persona")
  
    public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Persona persona = (Persona) o;
    return Objects.equals(this.nombre, persona.nombre) &&
        Objects.equals(this.apellido, persona.apellido) &&
        Objects.equals(this.dni, persona.dni) &&
        Objects.equals(this.edad, persona.edad);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, apellido, dni, edad);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Persona {\n");
    
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    apellido: ").append(toIndentedString(apellido)).append("\n");
    sb.append("    dni: ").append(toIndentedString(dni)).append("\n");
    sb.append("    edad: ").append(toIndentedString(edad)).append("\n");
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
