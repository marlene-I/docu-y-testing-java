
package mutation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @file Actividad.java
 * @brief Definición de la clase Actividad.
 * @date 2023-12-18
 * @author Marlene Carli, mcarli@exa.unicen.edu.ar
 */
public class Actividad implements Serializable{

	public Actividad(){	}

	public Actividad(String nombre, int cupo, int edadMinima){
		super();
		this.nombre = nombre;
		this.cupo = cupo;
		this.edadMinima = edadMinima;
		this.inscriptos = new ArrayList<>();
	}

	 /**
     * @brief Construye nueva actividad
	 * 
     * @param nombre    Nombre de la actividad.
     * @param encargado Persona encargada de la actividad.
     * @param cupo      Cupo máximo de participantes para la actividad.
     * @param edadMinima Edad mínima requerida para participar en la actividad.
     */
	public Actividad(String nombre, Persona encargado, int cupo, int edadMinima) {
		super();
		this.nombre = nombre;
		this.setEncargado(encargado);
		this.cupo = cupo;
		this.edadMinima = edadMinima;
		this.inscriptos = new ArrayList<>();
	}

	/**
	 * @brief Nombre de la actividad.
	 */
	String nombre;
	/**
	 * @brief Persona encargada de la actividad.
	 */
	Persona encargado;
	/**
	 * @brief Lista de socios inscritos en la actividad.
	 */
	List<Socio> inscriptos;
	/**
	 * @brief Edad mínima requerida para participar en la actividad.
	 */
	int edadMinima;

	/** 
     * @brief Cupo máximo de participantes para la actividad.
     */
	int cupo;


	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Persona getEncargado() {
		return encargado;
	}

	public void setEncargado(Persona encargado) {
		if (NominaSocios.YaExisteEnNomina(encargado)) {
			this.encargado = encargado;
		}
	}

	public List<Socio> getInscriptos() {
		return inscriptos;
	}

	public void setInscriptos(List<Socio> inscriptos) {
		this.inscriptos = inscriptos;
	}

	public int getCantidadLimiteParticipantes() {
		return cupo;
	}

	public void setCantidadLimiteParticipantes(int cupo) {
		this.cupo = cupo;
	}

	/**
     * @brief Inscribe a un socio en la actividad.
     * 
     * @param s Socio que se desea inscribir.
     * @throws CupoExcedidoException      Se lanza si el cupo de la actividad está excedido.
     * @throws EdadInsuficienteException Se lanza si la edad del socio no cumple con el requisito mínimo.
     */
	public void inscribirSocio(Socio s) throws CupoExcedidoException, EdadInsuficienteException {
		if (inscriptos == null)
			inscriptos = new ArrayList<>();
		if (this.getInscriptos().size() + 1 > this.getCupo())
			throw new CupoExcedidoException();
		if (s.getPersona().getEdad() < edadMinima)
			throw new EdadInsuficienteException();
		inscriptos.add(s);
	}

	/**
     * @brief Obtiene un socio inscripto en la actividad dado su DNI.
     * 
     * @param dni DNI del socio a buscar.
     * @return Socio inscripto con el DNI proporcionado, o null si no se encuentra.
     */
	public Socio getSocioInscripto(String dni) {
		for (Socio socio : inscriptos) {
			if (socio.getPersona().getDni().equals(dni))
				return socio;
		}
		return null;
	}

	/**
     * @brief Retorna un string que representa la actividad.
     * 
     * @return String con nombre de la actividad y nombre del encargado.
     */
	@Override
	public String toString() {
		return this.getNombre() + "  a cargo de " + this.getEncargado().getNombre();
	}

	@Override 
	public boolean equals(Object o){
		Actividad otraActividad = (Actividad) o;
		return otraActividad.getNombre() == this.getNombre();
	}
}
