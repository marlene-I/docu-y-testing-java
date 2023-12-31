package testing;

import java.io.Serializable;
import java.util.Date;

/**
 * @file Socio.java
 * @brief La clase Socio representa a un miembro del club, asociado con una
 *        persona y un identificador único.
 * @date 2023-12-18
 * 
 */

public class Socio implements Serializable {
	/**
	 * @brief Persona asociada al socio.
	 */
	Persona persona;
	/**
	 * @brief Identificador único del socio.
	 */
	int idSocio;
	/**
	 * @brief Fecha de ingreso del socio al club.
	 */
	Date fechaIngreso;

	public Socio (){}

	/**
	 * @brief Crea un objeto Socio a partir de una instancia de Persona.
	 * 
	 * @param p Persona asociada al socio.
	 */
	public Socio(Persona p) {
		this.persona = p;
	}

	/***
	 * Crea un objeto socio partiendo de tipos primitivos
	 * 
	 * @param nombre   Nombre del socio
	 * @param apellido Apellido del socio
	 * @param dni
	 * @param edad
	 */
	public Socio(String nombre, String apellido, String dni, int edad) {
		Persona p = new Persona(nombre, apellido, dni, edad);
		this.persona = p;
		this.setFechaIngreso(new Date());
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona p) {
		this.persona = p;
	}

	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String toString() {
		return this.getPersona().getApellido() + ", " + this.getPersona().getNombre();

	}
}
