import dev.failsafe.internal.util.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

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

    @Test
    public void handleMultipleTabs() throws InterruptedException {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("tabButton")).click();
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assertions.assertEquals(text, "This is a sample page");
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    @Test
    public void handleMultipleWindows() {
        driver.get("https://demoqa.com/browser-windows");
        driver.findElement(By.id("windowButton")).click();

//        here getWindowHandle is main window
        String mainWindow = driver.getWindowHandle();

//        here getWindowHandles means all pop up or child windows including the main window
        Set<String> allWindows = driver.getWindowHandles();

        Iterator<String> iterator = allWindows.iterator();

        while (iterator.hasNext()) {
            String childWindow = iterator.next();
            if (!mainWindow.equalsIgnoreCase(childWindow)){
                driver.switchTo().window(childWindow);
                String text = driver.findElement(By.id("sampleHeading")).getText();
                Assertions.assertTrue(text.contains("This is a sample page"));
            }
        }
        driver.close();
        driver.switchTo().window(mainWindow);
    }

    @Test
    public void scrapData() {
        driver.get("https://demoqa.com/webtables");
        WebElement table = driver.findElement(By.className("rt-tbody"));
        List<WebElement> allRows = table.findElements(By.className("rt-tr"));
        int i = 0;
        for (WebElement row : allRows){
//            here cells meant that particular one rows columns
            List<WebElement> cells = row.findElements(By.className("rt-td"));
            for(WebElement cell : cells){
                i++;
                System.out.println("num["+i+"]" + cell.getText());
            }
        }
    }

    @Test
    public void handleIFrame() {
        driver.get("https://demoqa.com/frames");
//        here frame1 is iframes id
        driver.switchTo().frame("frame1");
        String text = driver.findElement(By.id("sampleHeading")).getText();
        Assertions.assertTrue(text.contains("This is a sample page"));
//        leaving iframe
        driver.switchTo().defaultContent();
    }

    @AfterAll
    public void closeDriver(){
//        driver.quit();
    }

}
