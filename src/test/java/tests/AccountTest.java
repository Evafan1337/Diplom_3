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
import pages.*;
import utils.SeleniumHelper;

import static org.junit.Assert.assertEquals;


public class AccountTest {

    public WebDriver driver;

    private String existingUserMail = "test_user_ershov@mail.ru";
    private String existingUserPassword = "test_user_ershov";

    private String mainPageUrl = "https://stellarburgers.nomoreparties.site/";
    private String registerPageUrl = "https://stellarburgers.nomoreparties.site/register";
    private String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    private String accountProfileUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    private String accountProfileUrlNoLogin = "https://stellarburgers.nomoreparties.site/login";
    private String forgotPasswordPage = "https://stellarburgers.nomoreparties.site/forgot-password";

    private String feedUrl = "https://stellarburgers.nomoreparties.site/feed";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Step("Авторизация пользователя")
    public void loginUser() {

        LoginPage loginPage = new LoginPage();
        this.driver.findElement(loginPage.getEmailFieldInput()).sendKeys(this.existingUserMail);
        this.driver.findElement(loginPage.getPasswordFieldInput()).sendKeys(this.existingUserPassword);
        this.driver.findElement(loginPage.getLoginBtn()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));
    }

    @Test
    @DisplayName("Переход на страницу профиля с ленты с авторизацией")
    public void goToAccountFromFeedWithLogin() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        SeleniumHelper.clickOnElem(this.driver, mainPage.getFeedPageBtn());

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(feedUrl));

        FeedPage feedPage = new FeedPage();
        SeleniumHelper.clickOnElem(this.driver, feedPage.getAccountButton());

        String expected = this.accountProfileUrl;

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(expected, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу конструктора из профиля")
    public void goToConstructorFromProfile() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        SeleniumHelper.clickOnElem(this.driver, mainPage.getAccountButton());

        ProfilePage profilePage = new ProfilePage();
        SeleniumHelper.clickOnElem(this.driver, profilePage.getConstructorButton());

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(mainPageUrl, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу конструктора путем клику по логотипу")
    public void goToConstructorFromProfileViaLogo() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        SeleniumHelper.clickOnElem(this.driver, mainPage.getAccountButton());

        ProfilePage profilePage = new ProfilePage();
        SeleniumHelper.clickOnElem(this.driver, profilePage.getLogoLink());

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(mainPageUrl, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу конструктора со страницы логина")
    public void goToConstructorFromLogin() {
        this.driver.get(loginUrl);

        MainPage mainPage = new MainPage();
        SeleniumHelper.clickOnElem(this.driver, mainPage.getAccountButton());

        ProfilePage profilePage = new ProfilePage();
        SeleniumHelper.clickOnElem(this.driver, profilePage.getConstructorButton());

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(mainPageUrl, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу профиля без авторизации")
    public void goToAccountFromFeedWithNoLogin() {
        this.driver.get(mainPageUrl);

        MainPage mainPage = new MainPage();
        SeleniumHelper.clickOnElem(this.driver, mainPage.getFeedPageBtn());

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(feedUrl));

        FeedPage feedPage = new FeedPage();
        SeleniumHelper.clickOnElem(this.driver, feedPage.getAccountButton());

        String expected = this.accountProfileUrlNoLogin;

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(expected, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу профиля со страницы регистрации")
    public void goToAccountFromRegister() {

        this.driver.get(registerPageUrl);
        RegisterPage page = new RegisterPage();

        SeleniumHelper.clickOnElem(this.driver, page.getAccountButton());

        String expected = this.accountProfileUrlNoLogin;
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(expected, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу профиля со страницы восстановления пароля")
    public void goToAccountFromForgotPasswordPage() {
        this.driver.get(forgotPasswordPage);
        ForgotPasswordPage page = new ForgotPasswordPage();

        SeleniumHelper.clickOnElem(this.driver, page.getAccountButton());

        String expected = this.accountProfileUrlNoLogin;
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(expected, this.driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход на страницу конструктора со страницы профиля")
    public void goToConstructorFromAccount() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        SeleniumHelper.clickOnElem(this.driver, mainPage.getAccountButton());

        ProfilePage profilePage = new ProfilePage();
        SeleniumHelper.clickOnElem(this.driver, profilePage.getConstructorButton());

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(mainPageUrl, this.driver.getCurrentUrl());
    }

    @After
    public void teardown() {
        this.driver.quit();
    }

}
