package automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PruebaHumoAutomation {
    static WebDriver driver;
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("http://localhost:5500/frontend/asociar");

        InscripcionPage insPage = new InscripcionPage(driver);
        List<Persona> personas = new ArrayList<>();

        try {
            personas = CSVLoader.getPersonasAInscribir("automation\\src\\main\\resources\\personas.csv");
        } catch (Exception e) {
            System.out.println("No fue posible cargar el csv");
            e.printStackTrace();
        }

        // Persona p = personas.get(0);

        // insPage.cargarForm(p);
        // insPage.enviarForm();
        // System.out.println(insPage.getResultText());

        for (Persona persona : personas) {
            insPage.cargarForm(persona);
            insPage.enviarForm();
            System.out.println(insPage.getResultText());
        }
        

        driver.close();
    }
}
