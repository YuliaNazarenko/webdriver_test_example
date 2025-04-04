import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class TestCaseSDT21R108 {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


    @BeforeTest
    public void setup() {

        driver.manage().window().maximize();

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
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }

    //Ссылка на Jira https://ets-sdet.atlassian.net/browse/SDT21R-108

    @Test
    public void test108() throws InterruptedException {

        WebElement myAccount = driver.findElement(By.cssSelector("a[data-toggle='dropdown']"));
        myAccount.click();
        WebElement login = driver.findElement(By.id("pt-login-link"));
        login.click();

        WebElement submitLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[onclick*='ptlogin.loginAction']")));//здесь тест падал, добавлено время ожидания на появление кнопки Логин
        submitLogin.click();

        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='alert alert-danger']")));//здесь тоже для появления элемента в браузере нужно время

        String messageText = alertMessage.getText();
        System.out.println("messageText = " + messageText);
        String expectedText01 = "Warning: No match for E-Mail Address and/or Password.\n×";
        String expectedText02 = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.\n×";
        Assert.assertTrue(messageText.equals(expectedText01) || messageText.equals(expectedText02));
    }

    //Ссылка на Jira https://ets-sdet.atlassian.net/browse/SDT21R-114
    @Test
    public void test114_1() throws InterruptedException {  //Checks a notification when the item is added in the shopping bag

        driver.get("https://dgmarkt.com/index.php?route=product/product&path=62&product_id=7487331");

        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='button-cart']")));
        addToCart.click();

        sleep(3000);

        WebElement alertMessage = driver.findElement(By.xpath("//*[@class ='alert alert-fix alert-success alert-dismissible']"));
        String massageText = alertMessage.getText();
        Assert.assertEquals(massageText, "Success: You have added Nicky Clarke NSS111 Classic Ceramic & Tourmaline Straightner to your shopping cart!\n×");
    }

    @Test
    public void test114_2() throws InterruptedException {  //checks that an order placed as guest is successfully done

        driver.get("https://dgmarkt.com/index.php?route=product/product&path=62&product_id=7487331");

        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='button-cart']")));
        addToCart.click();

        WebElement shoppingBag = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='btn-group btn-block']")));
        shoppingBag.click();

        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='checkout/checkout']")));
        checkout.click();//****

        WebElement radioGuest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='guest']")));
        radioGuest.click();

        WebElement buttonContinue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[value='Continue']")));
        buttonContinue.click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-firstname']")));
        firstName.sendKeys("My first name");

        WebElement lastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-lastname']")));
        lastName.sendKeys("My last name");

        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-email']")));
        email.sendKeys("myemail@mail.com");

        WebElement telephone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-telephone']")));
        telephone.sendKeys("0123456789");

        WebElement company = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-company']")));
        company.sendKeys("My Company");

        WebElement address1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-address-1']")));
        address1.sendKeys("My street 22");

        WebElement address2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-address-2']")));
        address2.sendKeys("apart. 11");

        WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-city']")));
        city.sendKeys("Berlin");

        WebElement postCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-postcode']")));
        postCode.sendKeys("12277");

        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-country']")));
        Select selectCountry = new Select(country);
        selectCountry.selectByVisibleText("Germany");

        WebElement region = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='input-payment-zone']")));
        Select selectRegion = new Select(region);
        selectRegion.selectByContainsVisibleText("Berlin");

        WebElement buttonGuest = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='button-guest']")));
        buttonGuest.click();

        WebElement deliveryMassage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='comment']")));
        deliveryMassage.sendKeys("Lassen Sie bitte das Paket vor die Tür");

        WebElement deliveryButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='button-shipping-method']")));
        deliveryButton.click();

        WebElement paymentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='button-payment-method']")));
        paymentButton.click();

        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='alert alert-danger alert-dismissible']")));
        String massageText = alertMessage.getText();
        Assert.assertEquals(massageText, "Warning: You must agree to the Terms & Conditions!\n×");

        WebElement agrees = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='agree']")));
        agrees.click();
        paymentButton.click();

        WebElement confirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='button-confirm']")));
        confirm.click();

        sleep(500);

        WebElement finalMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='container-inner']")));
        String finalMessageText = finalMessage.getText();
        Assert.assertEquals(finalMessageText, "Your Order Has Been Placed!\nShopping Cart Checkout Success");
    }
}