package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.RegisterPage;

import java.util.concurrent.TimeUnit;

public class ConstructorTest {

    WebDriver driver;
    MainPage page;
    String url = "https://stellarburgers.nomoreparties.site";

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.get(url);
        this.page = new MainPage();
    }

    @Test
    public void clickOnSauce() throws Exception{
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(this.driver.findElement(page.getSauseHeader())));
        this.driver.findElement(page.getSauseHeader()).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(this.driver.findElement(page.getSauseConstructorHeader())));
        Thread.sleep(1000);
    }

    @Test
    public void clickOnBuns() throws Exception{


        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(this.driver.findElement(page.getSauseHeader())));
        this.driver.findElement(page.getSauseHeader()).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(this.driver.findElement(page.getSauseConstructorHeader())));
        Thread.sleep(1000);

        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(this.driver.findElement(page.getBunsHeader())));
        this.driver.findElement(page.getBunsHeader()).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(this.driver.findElement(page.getBunsConstructorHeader())));
        Thread.sleep(1000);
    }

    @Test
    public void clickOnFill() throws Exception {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(this.driver.findElement(page.getFillsHeader())));
        this.driver.findElement(page.getFillsHeader()).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(this.driver.findElement(page.getFillsConstructorHeader())));
        Thread.sleep(1000);
    }

    @After
    public void teardown(){
        this.driver.quit();
    }
}
