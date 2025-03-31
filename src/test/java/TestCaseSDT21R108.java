import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class TestCaseSDT21R108 {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @BeforeTest
    public void setup() {

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
    }

    @AfterTest
    public void quitBrowser() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void test108() throws InterruptedException {

        //Ссылка на Jira https://ets-sdet.atlassian.net/browse/SDT21R-108

        //TestRun
        WebElement myAccount = driver.findElement(By.cssSelector("a[data-toggle='dropdown']"));
        myAccount.click();
        WebElement login = driver.findElement(By.id("pt-login-link"));
        login.click();

        sleep(5000);
        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick*='ptlogin.loginAction']")));//здесь тест падал, добавлено время ожидания на появление кнопки Логин
        submitLogin.click();

        //sleep(5000);
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='alert alert-danger']")));//здесь тоже для появления элемента в браузере нужно время
        //sleep(5000);
        String messageText = alertMessage.getText();
        //System.out.println("messageText = " + messageText);
        String expectedText01 = "Warning: No match for E-Mail Address and/or Password\n×";
        String expectedText02 = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n×";
        Assert.assertTrue(
                messageText.equals(expectedText01) || messageText.equals(expectedText02)
        );
    }
}