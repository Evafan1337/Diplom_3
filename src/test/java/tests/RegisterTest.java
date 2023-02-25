package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RegisterTest {

    private WebDriver driver;
    private RegisterPage page;
    private String url = "https://stellarburgers.nomoreparties.site/register";
    private String succesRegisterRedirect = "https://stellarburgers.nomoreparties.site/login";

    @Step("Генерация тестовых данных")
    public String generateString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Step("Заполнение формы регистрации")
    public void passValuesInForm(String nameValue, String emailValue, String passwordValue) {
        driver.findElement(page.getNameInput()).sendKeys(nameValue);
        driver.findElement(page.getEmailInput()).sendKeys(emailValue);
        driver.findElement(page.getPasswordInput()).sendKeys(passwordValue);
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.get(url);
        this.page = new RegisterPage();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void successfulRegisterCheck() {

        String nameValue = this.generateString();
        String emailValue = this.generateString() + "@mail.ru";
        String passwordValue = "123456";

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(page.getRegisterButton()));

        this.passValuesInForm(nameValue, emailValue, passwordValue);

        driver.findElement(page.getRegisterButton()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(succesRegisterRedirect));

        assertEquals(driver.getCurrentUrl(), succesRegisterRedirect);
    }

    @Test
    @DisplayName("Неуспешная регистрация с установкой короткого пароля")
    public void failRegisterWithShortPassword() {

        String nameValue = this.generateString();
        String emailValue = this.generateString() + "@mail.ru";
        String passwordValue = "111";

        this.passValuesInForm(nameValue, emailValue, passwordValue);

        driver.findElement(page.getRegisterButton()).click();

        assertEquals(driver.getCurrentUrl(), url);
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
