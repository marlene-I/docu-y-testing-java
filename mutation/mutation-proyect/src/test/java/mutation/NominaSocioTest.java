package mutation;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NominaSocioTest {
    
    Integer idSocioActual;

    List<Socio> nominaClon;
    
    @BeforeClass
    public void inicializarClones(){
        idSocioActual = 0;
        nominaClon = new ArrayList<>();
        NominaSocios.limpiarNomina();
    }

    @DataProvider(name = "GeneradorPersonas")
    public Persona[] generadorPersonas(){
        return new Persona[] {
            new Persona("Maria", "Gomez", "12345678", 22),
            new Persona("Carlos", "Martinez", "87654321", 30),
            new Persona("Ana", "Sanchez", "23456789", 25),
            new Persona("Juan", "Fernandez", "34567890", 32),
            new Persona("Laura", "Diaz", "45678901", 28),
            new Persona("Pedro", "Perez", "56789012", 19),
            new Persona("Elena", "Rodriguez", "67890123", 25),
            new Persona("Francisco", "Lopez", "78901234", 18),
            new Persona("Isabel", "Torres", "89012345", 26),
            new Persona("Miguel", "Garcia", "90123456", 22),
        };
    }

    @Test(dataProvider = "GeneradorPersonas", priority = 1)
    public void asociarPersona(Persona p) throws YaExisteSocioException, EdadInsuficienteException{
        Socio s = new Socio(p);
        s.setIdSocio(idSocioActual);
        nominaClon.add(s);
        
        
        NominaSocios.Asociar(p);
        idSocioActual ++;
        // Assert.assertEquals(NominaSocios.GetNomina(), nominaClon);
        Assert.assertTrue(NominaSocios.GetNomina().contains(s));
        Assert.assertEquals(NominaSocios.getNextID() , idSocioActual);
    }

    @Test(priority = 2)
    public void bajaSocio() throws NoExisteSocioException{
        Socio s = new Socio(new Persona("Maria", "Gomez", "12345678", 22));
        NominaSocios.Baja(s);
        Assert.assertFalse( NominaSocios.GetNomina().contains(s));
    }

    @Test(priority = 3, expectedExceptions = NoExisteSocioException.class)
    public void bajaThrowsNoExisteSocio() throws NoExisteSocioException{
        Socio s = new Socio(new Persona("Maria", "Gomez", "12345678", 22));
        NominaSocios.Baja(s);
    }
}
