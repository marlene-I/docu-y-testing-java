package integrador;

public class EdadInsuficienteException extends Exception {
    public EdadInsuficienteException(){
        super("La edad no supera el valor mínimo requerido");
    }
}
