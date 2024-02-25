import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

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

    @Test
    public void demoQABtns() {
        driver.get("https://demoqa.com/buttons");

//        for many action effect
        Actions actions = new Actions(driver);
        List <WebElement> btns = driver.findElements(By.className("btn-primary"));

        actions.doubleClick(btns.get(0))
                .contextClick(btns.get(1))
                .perform();
        actions.click(btns.get(2)).perform();
    }

    @Test
    public void takeNewSS() throws IOException {
        driver.get("https://google.com");
        Utils.takeSS(driver);
    }

    @Test
    public void uploadFile() {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("uploadFile")).sendKeys(System.getProperty("user.dir") + "./src/test/resources/screenshots/25-02-2024-08-17-05-PM.png");
    }

    @AfterAll
    public void closeDriver(){
//        driver.quit();
    }

}
