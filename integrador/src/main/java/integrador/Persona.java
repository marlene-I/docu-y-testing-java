package integrador;

import java.io.Serializable;

/**
 * @file Persona.java
 * @brief La clase Persona representa a un individuo con atributos básicos
 * @date 2023-12-18
 * 
 */
public class Persona implements Serializable{
	/**
     * @brief Constructor de la clase Persona.
     * 
     * @param nombre   Nombre de la persona.
     * @param apellido Apellido de la persona.
     * @param dni      DNI (Documento Nacional de Identidad) de la persona.
     * @param edad     Edad de la persona.
     */
	 public Persona(String nombre, String apellido, String dni, int edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.edad = edad;
	}
	/**
     * @brief Nombre de la persona.
     */
	String nombre;
	String apellido;
	/**
     * @brief DNI (Documento Nacional de Identidad) de la persona.
     */
	String dni;
	/**
     * @brief Edad de la persona.
     */
	int edad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	 
	 
}
