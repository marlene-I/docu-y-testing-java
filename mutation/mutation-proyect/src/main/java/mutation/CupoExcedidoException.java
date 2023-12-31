package mutation;
/**
 * @file CupoExcedidoException.java
 * 
 * @brief Excepción para indicar que el límite de participantes ha sido excedido.
 * 
 * @details La excepción CupoExcedidoException se lanza cuando se intenta inscribir un socio en una actividad
 *          que ha alcanzado su límite de participantes (cupo máximo).
 *
 * @date 2023-12-18
 */
public class CupoExcedidoException extends Exception {

    public CupoExcedidoException(){
        super();
    }
}
