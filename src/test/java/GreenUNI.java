import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreenUNI {

    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void goGreenUNI() {
        driver.get("https://green.edu.bd");

//        for mouse hover
        Actions actions = new Actions(driver);
        List<WebElement> menuAdmission = driver.findElements(By.xpath("//a[contains(text(), \"Admissions\")]"));
        actions.moveToElement(menuAdmission.get(4)).perform();
    }

    @AfterAll
    public void closeDriver(){
//        driver.quit();
    }

}
