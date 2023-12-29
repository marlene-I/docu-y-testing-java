package integrador;

/**
 * @file NoExisteSocioException.java
 * @brief Excepción para indicar que el socio referenciado no existe.
 * @date 2023-12-18
 * 
 * La excepción NoExisteSocioException se lanza cuando se realiza una operación que requiere la existencia de un socio,
 * pero el socio no se encuentra en la lista o en el contexto esperado.
 */
public class NoExisteSocioException extends Exception {

      public NoExisteSocioException() {
        super();
    }
}