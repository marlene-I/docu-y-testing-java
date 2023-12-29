package swagger.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import integrador.CupoExcedidoException;
import integrador.EdadInsuficienteException;
import integrador.YaExisteSocioException;
import swagger.model.Actividad;
import swagger.model.Persona;
import swagger.model.Socio;
import swagger.services.NominaSociosService;
import swagger.services.OfertaActividadesService;


@Component
public class CSVLoader {

    public CSVParser getParser(String filePath) throws IOException {
        return CSVFormat.DEFAULT.withHeader().parse(new FileReader(filePath));
    }

    public void inicializar()
            throws IOException, NumberFormatException, YaExisteSocioException, EdadInsuficienteException {

        CSVParser parserPersonas = this.getParser("integrador\\src\\main\\java\\swagger\\resources\\personas.csv");
        ArrayList<Persona> personas = new ArrayList<>();

        for (CSVRecord row : parserPersonas) {
            Persona persona = new Persona(row.get("nombre"), row.get("apellido"), row.get("dni"),
                    Integer.parseInt(row.get("edad")));

            NominaSociosService.Asociar(persona);
            personas.add(persona);
        }

        CSVParser parserActividades = this.getParser("integrador\\src\\main\\java\\swagger\\resources\\actividades.csv");

        for (CSVRecord row : parserActividades) {
            Integer indicePersona = (int)(Math.random() * personas.size());
            Persona p = personas.get(indicePersona);

            Actividad act = new Actividad(row.get("nombre"), p,Integer.parseInt(row.get("cupo")), Integer.parseInt(row.get("edad_minima")));

            OfertaActividadesService.NuevaActividad(act);
        }

        ArrayList<Actividad> actividades = OfertaActividadesService.GetNominaActividades();
        ArrayList<Socio> nominaSocios = NominaSociosService.GetNomina();
        for (Actividad actividad : actividades) {
            Integer numIntegrantes = (int)(Math.random() * actividad.getCupo());

            for (int i = 0; i < numIntegrantes; i++) {
                Integer indiceSocio = (int)(Math.random() * nominaSocios.size());
                Socio s = nominaSocios.get(indiceSocio);
                try {
                    actividad.addInscriptosItem(s);
                    
                } catch (CupoExcedidoException e) {
                    System.out.println("El socio " + s.getNombreCompleto() + " no será inscripto a " + actividad.getNombre() + " porque se alcanzó el número máximo de participantes");
                } catch (EdadInsuficienteException e) {
                    System.out.println("El socio " + s.getNombreCompleto() + " no será inscripto a " + actividad.getNombre() + " ya que no tiene la edad mínima requerida");
                }
            }

        }

        System.out.println("Carga de datos finalizada");

    }

}
