import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;

public class BrowserInteractions {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstButton = driver.findElement(By.tagName("button"));
        firstButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        System.out.println("Alert text is " + "'" + text + "'");

        sleep(3000);

        alert.accept();

    }

    @Test
    public void test2() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement secondButton = driver.findElement(By.cssSelector("[onclick='jsConfirm()']"));
        secondButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();

        System.out.println("Alert text is " + "'" + text + "'");

        sleep(3000);

        alert.accept();

    }

    @Test
    public void testCookie() throws InterruptedException {

        driver.get("https://rozetka.com.ua/favicon.ico");

        driver.manage().addCookie(new Cookie("slang", "ru"));

        driver.get("https://rozetka.com.ua/");

        String title = driver.getTitle();

        System.out.println("title = " + title);

        String url = driver.getCurrentUrl();

        System.out.println("url = " + url);

        WebElement langButton = driver.findElement(By.cssSelector("[data-testid='lang_btn']"));

        String buttonText = langButton.getText();

        System.out.println("buttonText = " + buttonText);
    }

    @Test
    public void testIframe() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/iframe");

        WebElement iframe = driver.findElement(By.cssSelector("[id='mce_0_ifr']"));

        driver.switchTo().frame(iframe);

        WebElement editorParagraph = driver.findElement(By.cssSelector("body#tinymce p"));

        String paragraphText = editorParagraph.getText();

        System.out.println("Paragraph Text = " + paragraphText);
    }

    @Test
    public void testWindows() throws InterruptedException {

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        String myFirstTab = driver.getWindowHandle();

        sleep(2000);

        driver.switchTo().newWindow(WindowType.TAB);  //or
        //driver.switchTo().newWindow(WindowType.WINDOW);

        driver.get("https://the-internet.herokuapp.com/iframe");

        String mySecondTab = driver.getWindowHandle();

        WebElement iframe = driver.findElement(By.cssSelector("[id='mce_0_ifr']"));

        driver.switchTo().frame(iframe);

        WebElement editorParagraph = driver.findElement(By.cssSelector("body#tinymce p"));

        String paragraphText = editorParagraph.getText();

        System.out.println("paragraphText = " + paragraphText);

        Set<String> windowHandles = driver.getWindowHandles();

        sleep(2000);

        driver.switchTo().window(myFirstTab);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement secondButton = driver.findElement(By.cssSelector("[onclick='jsConfirm()']"));
        secondButton.click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        System.out.println("Alert text = " + text);

        sleep(2000);

        alert.accept();

        sleep(2000);

        driver.switchTo().window(mySecondTab).close();

        sleep(2000);
    }
}
