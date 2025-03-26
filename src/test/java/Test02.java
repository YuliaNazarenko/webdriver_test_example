import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class Test02 {
@Test
    public void testImdb() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.imdb.com/");
        driver.manage().window().maximize();
        sleep(3000);
        //WebElement menuButton = driver.findElement(By.cssSelector(".ipc-responsive-button__text"));
        WebElement menuButton = driver.findElement(By.xpath("//*[@class='ipc-responsive-button__text']"));
        menuButton.click();
        sleep(3000);


        driver.quit();

    }
}
