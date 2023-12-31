import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.CSVLoader;
import automation.InscripcionPage;
import automation.Persona;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InscripcionTest {

    private InscripcionPage inscPage;
    private WebDriver driver;

    @BeforeClass
    public void initPageFactory(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        inscPage = new InscripcionPage(driver);
    }

    @DataProvider(name="GeneradorPersonas")
    public Persona[] generatePersonas(){
        List<Persona> personas = new ArrayList<>();
        try {
            personas = CSVLoader.getPersonasAInscribir("src\\test\\data\\personas.csv");

        } catch (Exception e) {
            System.out.println("No fue posible cargar el csv");
            e.printStackTrace();
        }

        return personas.toArray(new Persona[0]);
    }

    @Test
    public void loadPage(){
        driver.get("http://localhost:5501/frontend/asociar");
        Assert.assertEquals(driver.getTitle(), "Manzanita JR");
    }
    
    @Test(dataProvider = "GeneradorPersonas")
    public void register(Persona persona){
        inscPage.cargarForm(persona);

        inscPage.enviarForm();

        Assert.assertEquals(inscPage.getResultText(), "Socio agregado");
    }

    @AfterClass
    public void closeDriver(){
        driver.close();
    }

}
