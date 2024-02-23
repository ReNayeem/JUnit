import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunitAutomation {

    WebDriver driver;

    @BeforeAll
    public void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void goDemoQA() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Md. Nayeem");
        driver.findElements(By.className("mr-sm-2")).get(1).sendKeys("ReNayeem@gmail.com");
        driver.findElements(By.tagName("textarea")).get(0).sendKeys("Killarpool");
        driver.findElements(By.tagName("textarea")).get(1).sendKeys("Loxmon Khola");

        Utils.scroll(driver, 0, 500);

        driver.findElements(By.cssSelector("[type='button']")).get(1).click();
        
        String actualName = driver.findElement(By.id("name")).getText();
        String expectedName = "Md. Nayeem";
        Assertions.assertTrue(actualName.contains(expectedName));
    }

    @AfterAll
    public void closeDriver(){
//        driver.quit();
    }

}
