import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestsInDifferentSites {

    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void goGoogle() {
        driver.get("https://google.com");

//        for many action effect
        Actions actions = new Actions(driver);
        WebElement searchElement = driver.findElement(By.name("q"));

        actions.moveToElement(searchElement)
                .keyDown(Keys.SHIFT)
                .sendKeys("FateNayeam")
                .keyUp(Keys.SHIFT)
                .doubleClick()
                .contextClick()
//                .keyDown(Keys.ENTER)
                .perform();
    }

    @AfterAll
    public void closeDriver(){
//        driver.quit();
    }

}
