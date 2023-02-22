package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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

    WebDriver driver;
    RegisterPage page;

    String existingUserLogin = "test_user_ershov";
    String existingUserMail = "test_user_ershov@mail.ru";
    String existingUserPassword = "test_user_ershov";

    String mainPageUrl = "https://stellarburgers.nomoreparties.site";
    String registerPageUrl = "https://stellarburgers.nomoreparties.site/register";
    String passwordRecoveryUrl = "https://stellarburgers.nomoreparties.site/forgot-password";
    String loginUrl = "https://stellarburgers.nomoreparties.site/login";
    String accountProfileUrl = "https://stellarburgers.nomoreparties.site/account/profile";

    public void loginUser() {

        //  Login and get loginValue
        LoginPage loginPage = new LoginPage();
        this.driver.findElement(loginPage.getEmailFieldInput()).sendKeys(this.existingUserMail);
        this.driver.findElement(loginPage.getPasswordFieldInput()).sendKeys(this.existingUserPassword);
        this.driver.findElement(loginPage.getLoginBtn()).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(mainPageUrl));
    }

    public void goToAccountPage() {
        MainPage page = new MainPage();
        this.driver.findElement(page.getAccountButton()).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(accountProfileUrl));
    }

    public String getCurrentUserLogin() {

        WebElement inputElem = this.driver.findElement(By.xpath("(//*[contains(text(),'Логин')])/following-sibling::input"));
        String loginValue = inputElem.getAttribute("value");
        return loginValue;
    }

    public void logoutUser() {
        ProfilePage page = new ProfilePage();
        this.driver.findElement(page.getLogoutBtn()).click();

    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @Test
    public void succesGoToLoginPageFromAccountBtn() {
        String expected = this.existingUserMail;


        MainPage page = new MainPage();
        this.driver.get(mainPageUrl);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(page.getGoToAccountButton()));
        this.driver.findElement(page.getGoToAccountButton()).click();
        assert this.driver.getCurrentUrl().equals(this.loginUrl);

        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();

        assertEquals(expected, result);
    }

    @Test
    public void succesGoToLoginPageFromGoToAccountBtn() throws Exception {
        MainPage page = new MainPage();
        this.driver.get(mainPageUrl);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(page.getAccountButton()));

        this.driver.findElement(page.getAccountButton()).click();

        assert this.driver.getCurrentUrl().equals(this.loginUrl);

        String expected = this.existingUserMail;
        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();

        assertEquals(expected, result);


    }

    @Test
    public void succesGoToLoginPageFromRegister() {
        RegisterPage page = new RegisterPage();
        this.driver.get(registerPageUrl);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(page.getGoToLoginButton()));

        this.driver.findElement(page.getGoToLoginButton()).click();

        assert this.driver.getCurrentUrl().equals(this.loginUrl);

        String expected = this.existingUserMail;
        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();

        assertEquals(expected, result);
    }

    @Test
    public void succesGoToLoginPageFromForgotPasswordPage() {
        ForgotPasswordPage page = new ForgotPasswordPage();
        this.driver.get(passwordRecoveryUrl);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(page.getGoToLoginButton()));
        this.driver.findElement(page.getGoToLoginButton()).click();

        assert this.driver.getCurrentUrl().equals(this.loginUrl);
        String expected = this.existingUserMail;
        this.loginUser();
        this.goToAccountPage();
        String result = this.getCurrentUserLogin();
        assertEquals(expected, result);
    }

    @Test
    public void succesLogout() {
        MainPage page = new MainPage();
        this.driver.get(mainPageUrl);

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(page.getGoToAccountButton()));
        this.driver.findElement(page.getGoToAccountButton()).click();
        assert this.driver.getCurrentUrl().equals(this.loginUrl);

        this.loginUser();
        this.goToAccountPage();
        this.logoutUser();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.urlMatches(loginUrl));

        assertEquals(this.driver.getCurrentUrl(), loginUrl);

    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
