package testng;

import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import integrador.Actividad;
import integrador.CupoExcedidoException;
import integrador.EdadInsuficienteException;
import integrador.Persona;
import integrador.Socio;
import integrador.YaExisteSocioException;
import integrador.NominaSocios;

/**
 * @file ActividadTest.java
 * @brief Clase de pruebas para la clase Actividad.
 * @date 2023-12-18
 * 
 * @details La clase ActividadTest contiene casos de prueba para verificar el
 *          funcionamiento correcto
 *          de la clase Actividad y sus métodos asociados, como la inscripción
 *          de socios, asignación de responsables,
 *          y manejo de excepciones.
 */
public class ActividadTest {

    /**
     * @brief Actividad utilizada para las pruebas.
     */
    Actividad actividad;

    /**
     * @brief Proveedor de datos para generar instancias de Socio.
     * 
     * @return Arreglo de socios generados para las pruebas.
     */
    @DataProvider(name = "GeneradorSocios")
    public Socio[] GeneradorSocios() {
        return new Socio[] {
                new Socio(new Persona("María", "Gómez", "12345678", 22)),
                new Socio(new Persona("Carlos", "Martínez", "87654321", 30)),
                new Socio(new Persona("Ana", "Sánchez", "23456789", 25)),
                new Socio(new Persona("Juan", "Fernández", "34567890", 32)),
                new Socio(new Persona("Laura", "Díaz", "45678901", 28)),
                new Socio(new Persona("Pedro", "Pérez", "56789012", 16)),
                new Socio(new Persona("Elena", "Rodríguez", "67890123", 25)),
                new Socio(new Persona("Francisco", "López", "78901234", 18)),
                new Socio(new Persona("Isabel", "Torres", "89012345", 26)),
                new Socio(new Persona("Miguel", "García", "90123456", 22)),
        };
    }

    /**
     * @brief Realiza la carga inicial de socios a la nómina del club.
     * 
     * @throws EdadInsuficienteException Si la edad del socio es insuficiente para
     *                                   ser asociado.
     * @throws YaExisteSocioException    Si el socio ya existe en la nómina.
     */
    @BeforeClass
    public static void cargaSociosANomina() throws EdadInsuficienteException, YaExisteSocioException {
        Persona[] Personas = new Persona[] {
                new Persona("María", "Gómez", "12345678", 22),
                new Persona("Carlos", "Martínez", "87654321", 30),
                new Persona("Ana", "Sánchez", "23456789", 25),
                new Persona("Juan", "Fernández", "34567890", 32),
                new Persona("Laura", "Díaz", "45678901", 28),
                new Persona("Pedro", "Pérez", "56789012", 19),
                new Persona("Elena", "Rodríguez", "67890123", 25),
                new Persona("Francisco", "López", "78901234", 18),
                new Persona("Isabel", "Torres", "89012345", 26),
                new Persona("Miguel", "García", "90123456", 22),
        };

        for (Persona persona : Personas) {
            NominaSocios.Asociar(persona);
        }
    }

    /**
     * @brief Crea una actividad de prueba inicial.
     */
    @BeforeTest()
    public void mockActividad() {
        this.actividad = new Actividad("Club de lectura", new Persona("Jorge Luis", "Borges", "1451123", 99), 5, 18);
    }

    /**
     * @brief Prueba la inscripción de un socio en una actividad.
     * 
     * @param socio Socio a inscribir.
     * 
     * @throws CupoExcedidoException     Si se supera el cupo máximo de la
     *                                   actividad.
     * @throws EdadInsuficienteException Si la edad del socio no cumple con
     *                                   la mínima requerida.
     */
    @Test(dataProvider = "GeneradorSocios")
    public void testInscripcionSocio(Socio socio) throws CupoExcedidoException, EdadInsuficienteException {

        if (this.actividad.getInscriptos().size() < this.actividad.getCupo()) {
            if (socio.getPersona().getEdad() < this.actividad.getEdadMinima()) {
                Assertions.assertThrows(EdadInsuficienteException.class, () -> {
                    this.actividad.inscribirSocio(socio);
                });
            } else {
                this.actividad.inscribirSocio(socio);
                Assert.assertSame(socio, actividad.getSocioInscripto(socio.getPersona().getDni()));
            }
        } else {
            Assertions.assertThrows(CupoExcedidoException.class, () -> {
                this.actividad.inscribirSocio(socio);
            });
        }

    }

    /**
     * @brief Prueba que la excepción EdadInsuficienteException sea lanzada
     *        correctamente si el socio no cumple con el mínimo requerido.
     * 
     * @throws EdadInsuficienteException Si la edad del socio con
     *                                   la mínima requerida.
     * @throws CupoExcedidoException     Si se supera el cupo máximo de la
     *                                   actividad.
     */
    @Test(expectedExceptions = EdadInsuficienteException.class)
    public void testNoInscribirSocioDeEdadInsuficiente() throws EdadInsuficienteException, CupoExcedidoException {
        Actividad act = new Actividad("Poker", new Persona("Jorge Luis", "Borges", "1451123", 99), 5, 18);
        act.inscribirSocio(new Socio(new Persona("Juan Jose", "Paso", "1233213", 17)));
    }

    /**
     * @brief Prueba que se lance la excepción CupoExcedidoException al intentar superar el cupo máximo de la actividad.
     * 
     * @throws EdadInsuficienteException Si la edad del socio no cumple con el requisito mínimo.
     * @throws CupoExcedidoException Si se supera el cupo máximo de la actividad.
     */
    @Test(expectedExceptions = CupoExcedidoException.class)
    public void testNoSuperarCupo() throws EdadInsuficienteException, CupoExcedidoException {
        Actividad act = new Actividad("Poker", new Persona("Jorge Luis", "Borges", "1451123", 99), 0, 18);

        act.inscribirSocio(new Socio(new Persona("Juan Jose", "Paso", "1233213", 20)));
    }

    /**
     * @brief Proveedor de datos para generar instancias de Persona.
     * 
     * @return Arreglo de personas generadas para las pruebas.
     */
    @DataProvider(name = "GeneradorPersonas")
    public Persona[] generadorPersonas() {
        return new Persona[] {
                new Persona("María", "Gómez", "12345678", 22),
                new Persona("Carlos", "Martínez", "87654321", 30),
                new Persona("Ana", "Sánchez", "23456789", 25),
                new Persona("Juan", "Fernández", "34567890", 32),
                new Persona("Laura", "Díaz", "45678901", 28),
                new Persona("Pedro", "Pérez", "56789012", 19),
                new Persona("Elena", "Rodríguez", "67890123", 25),
                new Persona("Francisco", "López", "78901234", 18),
                new Persona("Isabel", "Torres", "89012345", 26),
                new Persona("Miguel", "García", "90123456", 22),
                new Persona("Ana", "Martínez", "78901534", 28),
                new Persona("Carlos", "López", "56789112", 35),
                new Persona("Laura", "Sánchez", "43210987", 31),
                new Persona("Pedro", "Fernández", "65432109", 26),
                new Persona("Isabel", "Díaz", "32345678", 24)
        };

    }

    /**
     * @brief Prueba la asignación de un socio como responsable de la actividad.
     * 
     * @param persona Persona a asignar como responsable.
     */
    @Test(dataProvider = "GeneradorPersonas")
    public void testSetEncargado(Persona persona) {
        /*
         * Un test que verifique que sólo puede asignarse como responsable a un socio de
         * la nómina
         */
        if (NominaSocios.YaExisteEnNomina(persona)) {
            this.actividad.setEncargado(persona);
            Assert.assertSame(this.actividad.getEncargado(), persona);
        } else {
            Assert.assertNotSame(this.actividad.getEncargado(), persona);
        }

    }

}
