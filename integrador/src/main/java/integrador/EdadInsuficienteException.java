package integrador;

/**
 * @file EdadInsuficienteException.java
 * @brief Excepción para indicar que una persona no tiene edad suficiente para una acción.
 * @date 2023-12-18
 * 
 * @details La excepción EdadInsuficienteException se lanza cuando se intenta inscribir realizar una acción y 
 *          la persona involucrada no cumple con la edad mínima requerida para tal fin.
 */
public class EdadInsuficienteException extends Exception {

    public EdadInsuficienteException() {
        super();
    }
}
