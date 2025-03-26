import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class TestCaseSDT21R108 {
    @Test
    public void test108() throws InterruptedException {

        //Ссылка на Jira https://ets-sdet.atlassian.net/browse/SDT21R-108

        WebDriver driver = new ChromeDriver();
        driver.get("https://dgmarkt.com/login.php");

        //Preconditions
        WebElement userName = driver.findElement(By.cssSelector("input[name='email']"));
        userName.sendKeys("ders@dgmarkt.com");
        WebElement password = driver.findElement(By.cssSelector("input[name='password']"));
        password.sendKeys("2574dGlogin");
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();
        WebElement closeSignUp = driver.findElement(By.xpath("//*[@class='a-close-newsletter']"));
        closeSignUp.click();

        //TestRun
        WebElement myAccount = driver.findElement(By.cssSelector("a[data-toggle='dropdown']"));
        myAccount.click();
        WebElement login = driver.findElement(By.id("pt-login-link"));
        login.click();

        sleep(500);
        WebElement submitLogin = driver.findElement(By.cssSelector("[onclick*='ptlogin.loginAction']"));//здесь тест падал, добавлено время ожидания на появление кнопки Логин
        submitLogin.click();

        sleep(500);
        WebElement alertMessage = driver.findElement(By.xpath("//*[@class='alert alert-danger']"));//здесь тоже для повления элемента в браузере нужно время
        String messageText = alertMessage.getText();
        System.out.println("messageText = " + messageText);
        driver.quit();
    }
}
