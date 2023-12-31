package automation;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVLoader {

    public static CSVParser getParser(String filePath) throws IOException {
        return CSVFormat.DEFAULT.withHeader().parse(new FileReader(filePath));
    }

    public static List<Persona> getPersonasAInscribir(String filePath) throws IOException{

        CSVParser parserPersonas = getParser(filePath);
        List<Persona> personas = new ArrayList<>();

        for (CSVRecord row : parserPersonas) {
            personas.add(new Persona(row.get("nombre"), row.get("apellido"), row.get("dni"),
            Integer.parseInt(row.get("edad"))));

        }
        
        return personas;
    }

    

}
