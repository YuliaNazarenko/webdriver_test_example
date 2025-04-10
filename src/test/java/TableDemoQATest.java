import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TableDemoQATest {

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

    void fillingForm01() throws InterruptedException, AWTException {
        driver.get("https://demoqa.com/automation-practice-form");


        // Убираем рекламу.
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("$('footer').remove();");
        executor.executeScript("$('#fixedban').remove();");
        executor.executeScript("$('iframe').remove();");

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Shah Rukh");

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Khan");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("abc@abc.com");

        WebElement mobileNumber = driver.findElement(By.id("userNumber"));
        mobileNumber.sendKeys("1234567890");

        WebElement gender = driver.findElement(By.xpath("//label[text()='Male']"));
        gender.click();

        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirth.click();
        for (int i = 0; i < 10; i++) {
            dateOfBirth.sendKeys(Keys.BACK_SPACE);
        }

        dateOfBirth.sendKeys("11.02.1965");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement subject = wait.until(ExpectedConditions.elementToBeClickable(By.id("subjectsInput")));
        subject.sendKeys("computer science");
        subject.sendKeys(Keys.ENTER);
        subject.sendKeys("math");
        subject.sendKeys(Keys.ENTER);
        subject.sendKeys("english");
        subject.sendKeys(Keys.ENTER);
        sleep(1000);

        WebElement hobbiesCheckListSports = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"));
        hobbiesCheckListSports.click();
        WebElement hobbiesCheckListReading = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-2']"));
        hobbiesCheckListReading.click();
        WebElement hobbiesCheckListMusic = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-3']"));
        hobbiesCheckListMusic.click();

        WebElement uploadFileButton = driver.findElement(By.cssSelector("label[for='uploadPicture']"));
        uploadFileButton.click();

        sleep(500);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection("\"C:\\Users\\euroTech Study\\Desktop\\Снимок экрана 2025-03-20 174907.png\""), null);

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        Thread.sleep(100);

        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);


        Thread.sleep(500);

        robot.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
        Thread.sleep(3000);

        WebElement textArea = driver.findElement(By.cssSelector("[placeholder='Current Address']"));
        textArea.sendKeys("75222");
        textArea.sendKeys(Keys.ENTER);
        textArea.sendKeys("Bergstr, 15");

        WebElement stateSelection = driver.findElement(By.xpath("//*[@class=' css-tlfecz-indicatorContainer']"));
        stateSelection.click();

        WebElement stateOption = driver.findElement(By.xpath("//div[text()='NCR']"));
        stateOption.click();

        WebElement citySelection = driver.findElement(By.xpath("//*[@class=' css-1wa3eu0-placeholder']"));
        citySelection.click();

        WebElement cityOption = driver.findElement(By.xpath("//div[text()='Delhi']"));
        cityOption.click();


        sleep(1000);

        WebElement buttonSubmit = driver.findElement(By.id("submit"));
        executor.executeScript("arguments[0].scrollIntoView(true);", buttonSubmit);
        buttonSubmit.click();
    }

    @Test
    public void demoqa() throws InterruptedException, AWTException{

        //@RepeatedTest(3)
        fillingForm01();

        sleep(1000);

        WebElement resultsTable = driver.findElement(By.cssSelector(".modal-body table"));
        assertTrue(resultsTable.isDisplayed());

//        WebElement tableStudentName = driver.findElement(By.xpath("//table/tbody/tr/td[2]"));
//        assertEquals("Shah Rukh Khan", tableStudentName.getText());
//
//        WebElement tableCellStudentEmail = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]"));
//        assertEquals("abc@abc.com", tableCellStudentEmail.getText());
//
//        WebElement tableMobile = driver.findElement(By.xpath("//table//td[contains(text(),'Mobile')]/../td[2]"));
//        assertEquals("1234567890",tableMobile.getText());

        checkTableCell("Student Name", "Shah Rukh Khan");
        checkTableCell("Student Email", "abc@abc.com");
        checkTableCell("Gender", "Male");
        checkTableCell("Date of Birth", "02 November,1965");
        checkTableCell("Subject", "Computer Science, Maths, English");
        checkTableCell("Hobbies", "Sports, Reading, Music");
        checkTableCell("Picture", "Снимок экрана 2025-03-20 174907.png");
        checkTableCell("Address", "75222 Bergstr, 15");
        checkTableCell("State and City", "NCR Delhi");

    }

    void checkTableCell(String cellName, String cellValue) {
        String locator = String.format("//table//td[contains(text(),'%s')]/../td[2]", cellName);
        WebElement tableCell = driver.findElement(By.xpath(locator));
        assertEquals(cellValue, tableCell.getText());
    }
}