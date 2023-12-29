package swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import integrador.CupoExcedidoException;
import integrador.EdadInsuficienteException;

import com.fasterxml.jackson.annotation.JsonCreator;
import swagger.model.Persona;
import swagger.model.Socio;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Actividad
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-29T01:15:12.905934647Z[GMT]")


public class Actividad   {
  @JsonProperty("nombre")
  private String nombre = null;

  @JsonProperty("encargado")
  private Persona encargado = null;

  @JsonProperty("inscriptos")
  @Valid
  private List<Socio> inscriptos = null;

  @JsonProperty("edadMinima")
  private Integer edadMinima = null;

  @JsonProperty("cupo")
  private Integer cupo = null;

  public Actividad nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  public Actividad(String nombre, Persona encargado, int cupo, int edadMinima) {
		super();
		this.nombre = nombre;
		this.setEncargado(encargado);
		this.cupo = cupo;
		this.edadMinima = edadMinima;
		this.inscriptos = new ArrayList<Socio>();
	}

  /**
   * Nombre de la actividad
   * @return nombre
   **/
  @Schema(description = "Nombre de la actividad")
  
    public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Actividad encargado(Persona encargado) {
    this.encargado = encargado;
    return this;
  }

  /**
   * Get encargado
   * @return encargado
   **/
  @Schema(description = "")
  
    @Valid
    public Persona getEncargado() {
    return encargado;
  }

  public void setEncargado(Persona encargado) {
    this.encargado = encargado;
  }

  public Actividad inscriptos(List<Socio> inscriptos) {
    this.inscriptos = inscriptos;
    return this;
  }

 public void addInscriptosItem(Socio s) throws CupoExcedidoException, EdadInsuficienteException {
		if (inscriptos == null)
			inscriptos = new ArrayList<Socio>();
		if (this.getInscriptos().size() + 1 > this.getCupo())
			throw new CupoExcedidoException();
		if (s.getPersona().getEdad() < edadMinima)
			throw new EdadInsuficienteException();
		inscriptos.add(s);
	}

  /**
   * Lista de socios inscritos en la actividad
   * @return inscriptos
   **/
  @Schema(description = "Lista de socios inscritos en la actividad")
      @Valid
    public List<Socio> getInscriptos() {
    return inscriptos;
  }

  public void setInscriptos(List<Socio> inscriptos) {
    this.inscriptos = inscriptos;
  }

  public Actividad edadMinima(Integer edadMinima) {
    this.edadMinima = edadMinima;
    return this;
  }

  /**
   * Edad mínima requerida para participar en la actividad
   * @return edadMinima
   **/
  @Schema(description = "Edad mínima requerida para participar en la actividad")
  
    public Integer getEdadMinima() {
    return edadMinima;
  }

  public void setEdadMinima(Integer edadMinima) {
    this.edadMinima = edadMinima;
  }

  public Actividad cupo(Integer cupo) {
    this.cupo = cupo;
    return this;
  }

  /**
   * Cantidad máxima de inscritos permitidos en la actividad
   * @return cupo
   **/
  @Schema(description = "Cantidad máxima de inscritos permitidos en la actividad")
  
    public Integer getCupo() {
    return cupo;
  }

  public void setCupo(Integer cupo) {
    this.cupo = cupo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Actividad actividad = (Actividad) o;
    return Objects.equals(this.nombre, actividad.nombre) &&
        Objects.equals(this.encargado, actividad.encargado) &&
        Objects.equals(this.inscriptos, actividad.inscriptos) &&
        Objects.equals(this.edadMinima, actividad.edadMinima) &&
        Objects.equals(this.cupo, actividad.cupo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, encargado, inscriptos, edadMinima, cupo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Actividad {\n");
    
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    encargado: ").append(toIndentedString(encargado)).append("\n");
    sb.append("    inscriptos: ").append(toIndentedString(inscriptos)).append("\n");
    sb.append("    edadMinima: ").append(toIndentedString(edadMinima)).append("\n");
    sb.append("    cupo: ").append(toIndentedString(cupo)).append("\n");
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
