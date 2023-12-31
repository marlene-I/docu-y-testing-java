package automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InscripcionPage {
    WebDriver webDriver;

    @FindBy(name = "nombre")
    private WebElement inputNombre;
    
    @FindBy(name = "apellido")
    private WebElement inputApellido;
    
    @FindBy(name = "edad")
    private WebElement inputEdad;

    @FindBy( name = "dni")
    private WebElement inputDNI;

    @FindBy(id = "register-button")
    private WebElement registerBtn;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/h4")
    private WebElement registerResultText;

    public InscripcionPage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(this.webDriver, this);
    }

    public void cargarNombre(String nombre){
        inputNombre.sendKeys(nombre);
    }

    public void cargarApellido(String apellido){
        inputApellido.sendKeys(apellido);
    }

    public void cargarEdad(Integer edad){
        inputEdad.sendKeys(Integer.toString(edad));
    }

    public void cargarDNI(String dni){
        inputDNI.sendKeys(dni);
    }
    
    public void cargarForm(Persona p){
        cargarNombre(p.getNombre());
        cargarApellido(p.getApellido());
        cargarDNI(p.getDni());
        cargarEdad(p.getEdad());
    }

    public void enviarForm(){
        registerBtn.click();
    }

    public String getResultText(){

        Duration time = Duration.ofMillis(4000);
        WebDriverWait wait = new WebDriverWait(webDriver, time);
        this.registerResultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[2]/h4")));

        return registerResultText.getText();
    }
    
}
    