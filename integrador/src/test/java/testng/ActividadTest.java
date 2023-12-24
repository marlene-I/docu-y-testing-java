package testng;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import integrador.Actividad;
import integrador.CupoExcedidoException;
import integrador.EdadInsuficienteException;
import integrador.Persona;
import integrador.Socio;

public class ActividadTest {

    Actividad actividad;

    @DataProvider(name = "GeneradorSocios")
    public Socio[] GeneradorSocios(){
        return new Socio[] {
            new Socio(new Persona("María","Gómez","12345678",22)),
            new Socio(new Persona("Carlos","Martínez","87654321",30)),
            new Socio(new Persona("Ana","Sánchez","23456789",25)),
            new Socio(new Persona("Juan","Fernández","34567890",32)),
            new Socio(new Persona("Laura","Díaz","45678901",28)),
            new Socio(new Persona("Pedro","Pérez","56789012",19)),
            new Socio(new Persona("Elena","Rodríguez","67890123",25)),
            new Socio(new Persona("Francisco","López","78901234",18)),
            new Socio(new Persona("Isabel","Torres","89012345",26)),
            new Socio(new Persona("Miguel","García","90123456",22)),
        };
    }

    @BeforeTest()
    public void mockActividad(){
        this.actividad = new Actividad("Club de lectura", new Persona("Jorge Luis", "Borges", "1451123", 99), 5, 18);
    }
    

    @Test(dataProvider = "GeneradorSocios")
    public void testInscripcionSocio(Socio socio) throws CupoExcedidoException, EdadInsuficienteException{
    /*
        * Un test que consuma los socios generados en el inciso anterior y verifique que sólo pueden
        * inscribirse socios si hay cupo y si lo permite la edad. Tener en cuenta que el modelo tiene
        * excepciones para tal fin y que, también debe comprobarse su correcto disparo
        */
        this.actividad.inscribirSocio(socio);
        Assert.assertSame(socio, actividad.getSocioInscripto(socio.getPersona().getDni()));
    }
        
    @Test(expectedExceptions = EdadInsuficienteException.class)
    public void testNoInscribirSocioDeEdadInsuficiente() throws EdadInsuficienteException, CupoExcedidoException{       
        this.actividad.inscribirSocio(new Socio(new Persona("Juan Jose", "Paso", "1233213", 17)));
    }

    @Test(expectedExceptions = CupoExcedidoException.class)
    public void testNoSuperarCupo() throws EdadInsuficienteException, CupoExcedidoException{     
        Actividad act = new Actividad("Poker", new Persona("Jorge Luis", "Borges", "1451123", 99), 0, 18);

        act.inscribirSocio(new Socio(new Persona("Juan Jose", "Paso", "1233213", 20)));
    }

}
