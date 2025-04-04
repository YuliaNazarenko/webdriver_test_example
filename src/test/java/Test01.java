import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class Test01 {
    @Test
    public void test01() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/login");
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tomsmith");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();
        WebElement logOut = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logOut.isDisplayed());
        sleep(3000);
        logOut.click();
        sleep(3000);
        WebElement flashMassage = driver.findElement(By.id("flash"));
        String messageText = flashMassage.getText();
        System.out.println("messageText = " + messageText);
        driver.quit();

    }
}
