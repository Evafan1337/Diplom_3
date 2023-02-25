package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;

    private String existingUserMail = "test_user_ershov@mail.ru";
    private String existingUserPassword = "test_user_ershov";

    private String mainPageUrl = "https://stellarburgers.nomoreparties.site";
    private String registerPageUrl = "https://stellarburgers.nomoreparties.site/register";
    private String passwordRecoveryUrl = "https://stellarburgers.nomoreparties.site/forgot-password";
    private String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private String accountProfileUrl = "https://stellarburgers.nomoreparties.site/account/profile";


    @Step("Авторизация пользователя")
    public void loginUser() {
        LoginPage loginPage = new LoginPage();
        this.driver.findElement(loginPage.getEmailFieldInput()).sendKeys(this.existingUserMail);
        this.driver.findElement(loginPage.getPasswordFieldInput()).sendKeys(this.existingUserPassword);
        this.driver.findElement(loginPage.getLoginBtn()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));
    }

    @Step("Переход на страницу профиля")
    public void goToAccountPage() {
        MainPage page = new MainPage();
        this.driver.findElement(page.getAccountButton()).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(accountProfileUrl));
    }

    @Step("Получение данных о текущем авторизованном пользователе")
    public String getCurrentUserLogin() {

        WebElement inputElem = this.driver.findElement(By.xpath("(//*[contains(text(),'Логин')])/following-sibling::input"));
        String loginValue = inputElem.getAttribute("value");
        return loginValue;
    }

    @Step("Выход из профиля")
    public void logoutUser() {
        ProfilePage page = new ProfilePage();
        this.driver.findElement(page.getLogoutBtn()).click();

    }

    @Step("Переход на страницу логина")
    public void goToLoginPage(By locator) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(locator));
        this.driver.findElement(locator).click();
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @Test
    @DisplayName("Переход на страницу логина путем нажатия на кнопку 'Войти в аккаунт'")
    public void succesGoToLoginPageFromAccountBtn() {
        String expected = this.existingUserMail;
        this.driver.get(mainPageUrl);

        MainPage page = new MainPage();
        this.goToLoginPage(page.getGoToAccountButton());

        assertEquals(this.loginUrl, this.driver.getCurrentUrl());

        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Переход на страницу логина путем нажатия на кнопку 'Личный Кабинет'")
    public void succesGoToLoginPageFromGoToAccountBtn() throws Exception {
        String expected = this.existingUserMail;
        this.driver.get(mainPageUrl);

        MainPage page = new MainPage();
        this.goToLoginPage(page.getAccountButton());

        assertEquals(this.loginUrl, this.driver.getCurrentUrl());

        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();

        assertEquals(expected, result);


    }

    @Test
    @DisplayName("Переход на страницу логина со страницы регистрации")
    public void succesGoToLoginPageFromRegister() {
        String expected = this.existingUserMail;
        this.driver.get(registerPageUrl);

        RegisterPage page = new RegisterPage();
        this.goToLoginPage(page.getGoToLoginButton());

        assertEquals(this.loginUrl, this.driver.getCurrentUrl());

        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Переход на страницу логина со страницы восстановления пароля")
    public void succesGoToLoginPageFromForgotPasswordPage() {
        String expected = this.existingUserMail;
        this.driver.get(passwordRecoveryUrl);

        ForgotPasswordPage page = new ForgotPasswordPage();
        this.goToLoginPage(page.getGoToLoginButton());

        assertEquals(this.loginUrl, this.driver.getCurrentUrl());

        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Выход из профиля")
    public void succesLogout() {
        this.driver.get(mainPageUrl);

        MainPage page = new MainPage();
        this.goToLoginPage(page.getGoToAccountButton());

        assertEquals(this.loginUrl, this.driver.getCurrentUrl());

        this.loginUser();
        this.goToAccountPage();
        this.logoutUser();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(loginUrl));

        assertEquals(this.loginUrl, this.driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
