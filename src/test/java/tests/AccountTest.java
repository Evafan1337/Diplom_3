package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import static org.junit.Assert.assertEquals;


//@RunWith(Parameterized.class)
public class AccountTest {

    boolean loginParam;
    WebDriver driver;

    //TO-DO: make DataClass static
    String existingUserLogin = "test_user_ershov";
    String existingUserMail = "test_user_ershov@mail.ru";
    String existingUserPassword = "test_user_ershov";


    //TO-DO: make DataClass static
    String mainPageUrl = "https://stellarburgers.nomoreparties.site/";
    String registerPageUrl = "https://stellarburgers.nomoreparties.site/register";
    String passwordRecoveryUrl = "https://stellarburgers.nomoreparties.site/forgot-password";
    String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    String accountProfileUrl = "https://stellarburgers.nomoreparties.site/account/profile";
    String accountProfileUrlNoLogin = "https://stellarburgers.nomoreparties.site/login";
    String forgotPasswordPage = "https://stellarburgers.nomoreparties.site/forgot-password";

    String feedUrl = "https://stellarburgers.nomoreparties.site/feed";

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    public void loginUser() {

        //  Login and get loginValue
        LoginPage loginPage = new LoginPage();
        this.driver.findElement(loginPage.getEmailFieldInput()).sendKeys(this.existingUserMail);
        this.driver.findElement(loginPage.getPasswordFieldInput()).sendKeys(this.existingUserPassword);
        this.driver.findElement(loginPage.getLoginBtn()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));
    }

    @Test
    public void goToAccountFromFeedWithLogin() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        this.driver.findElement(mainPage.getFeedPageBtn()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(feedUrl));

        FeedPage feedPage = new FeedPage();
        this.driver.findElement(feedPage.getAccountButton()).click();

        //Refactor
        String expected = this.accountProfileUrl;

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(this.driver.getCurrentUrl(), expected);
    }

    @Test
    public void goToConstructorFromProfile() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        this.driver.findElement(mainPage.getAccountButton()).click();

        ProfilePage profilePage = new ProfilePage();
        this.driver.findElement(profilePage.getConstructorButton()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(this.driver.getCurrentUrl(), mainPageUrl);
    }

    @Test
    public void goToConstructorFromProfileViaLogo() {
        this.driver.get(loginUrl);
        this.loginUser();

        MainPage mainPage = new MainPage();
        this.driver.findElement(mainPage.getAccountButton()).click();

        ProfilePage profilePage = new ProfilePage();
        this.driver.findElement(profilePage.getLogoLink()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(this.driver.getCurrentUrl(), mainPageUrl);
    }

    @Test
    public void goToConstructorFromLogin() {
        this.driver.get(loginUrl);

        MainPage mainPage = new MainPage();
        this.driver.findElement(mainPage.getAccountButton()).click();

        ProfilePage profilePage = new ProfilePage();
        this.driver.findElement(profilePage.getConstructorButton()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(this.driver.getCurrentUrl(), mainPageUrl);
    }

    @Test
    public void goToAccountFromFeedWithNoLogin() {
        this.driver.get(mainPageUrl);

        MainPage mainPage = new MainPage();
        this.driver.findElement(mainPage.getFeedPageBtn()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(feedUrl));

        FeedPage feedPage = new FeedPage();
        this.driver.findElement(feedPage.getAccountButton()).click();

        String expected = this.accountProfileUrlNoLogin;

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(this.driver.getCurrentUrl(), expected);
    }

    @Test
    public void goToAccountFromRegister() {

        this.driver.get(registerPageUrl);
        RegisterPage page = new RegisterPage();

        this.driver.findElement(page.getAccountButton()).click();

        String expected = this.accountProfileUrlNoLogin;
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(this.driver.getCurrentUrl(), expected);
    }

    @Test
    public void goToAccountFromForgotPasswordPage() {
        this.driver.get(forgotPasswordPage);
        ForgotPasswordPage page = new ForgotPasswordPage();

        this.driver.findElement(page.getAccountButton()).click();

        String expected = this.accountProfileUrlNoLogin;
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(expected));

        assertEquals(this.driver.getCurrentUrl(), expected);
    }

    @Test
    public void goToConstructorFromAccount() {
        this.driver.get(loginUrl);
        this.loginUser();


        MainPage mainPage = new MainPage();
        this.driver.findElement(mainPage.getAccountButton()).click();

        ProfilePage profilePage = new ProfilePage();
        this.driver.findElement(profilePage.getConstructorButton()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));

        assertEquals(this.driver.getCurrentUrl(), mainPageUrl);
    }

    @After
    public void teardown() {
        this.driver.quit();
    }

}
