import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void scroll(WebDriver driver, int width, int height){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy("+width+", "+height+")", "");
    }

    public static void takeSS(WebDriver driver) throws IOException {
//        driver.get("https://google.com");

//        need apache commons io from mvnrepo
        File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String name = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        File saveLocation = new File("./src/test/resources/screenshots/" + name + ".png");
        FileUtils.copyFile(ss, saveLocation);

    }

}
