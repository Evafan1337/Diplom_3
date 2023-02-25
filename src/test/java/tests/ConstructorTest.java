package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private WebDriver driver;
    private MainPage page;
    private String url = "https://stellarburgers.nomoreparties.site";

    @Step("Клик по пункту конструктора")
    public void constructorClick(By elemLocator, By headerLocator) throws Exception {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(this.driver.findElement(elemLocator)));
        this.driver.findElement(elemLocator).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(this.driver.findElement(headerLocator)));
        Thread.sleep(1000);
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.get(url);
        this.page = new MainPage();
    }

    @Test
    @DisplayName("Нажатие по пункту 'Соусы'")
    public void clickOnSauce() throws Exception {
        this.constructorClick(page.getSauseHeader(), page.getSauseConstructorHeader());

        assertTrue(this.driver.findElement(page.getSauseConstructorHeader()).isDisplayed());
    }

    @Test
    @DisplayName("Нажатие по пункту 'Булки' после нажатия по пункту 'Соусы'")
    public void clickOnBuns() throws Exception {

        this.constructorClick(page.getSauseHeader(), page.getSauseConstructorHeader());
        assertTrue(this.driver.findElement(page.getSauseConstructorHeader()).isDisplayed());

        this.constructorClick(page.getBunsHeader(), page.getBunsConstructorHeader());
        assertTrue(this.driver.findElement(page.getBunsConstructorHeader()).isDisplayed());

    }

    @Test
    @DisplayName("Нажатие по пункту 'Начинки'")
    public void clickOnFill() throws Exception {
        this.constructorClick(page.getFillsHeader(), page.getFillsConstructorHeader());
        assertTrue(this.driver.findElement(page.getFillsConstructorHeader()).isDisplayed());
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
