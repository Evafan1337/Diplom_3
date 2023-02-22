package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    WebDriver driver;
    private By nameInput = By.xpath("//*[contains(text(),'Имя')]/following-sibling::input");
    private By emailInput = By.xpath("//*[contains(text(),'Email')]/following-sibling::input");
    private By passwordInput = By.xpath("//*[contains(text(),'Пароль')]/following-sibling::input");
    private By registerButton = By.xpath("//*[contains(text(),'Зарегистрироваться')]");
    private By goToLoginButton = By.xpath("//*[contains(text(),'Войти')]");

    public By getGoToLoginButton() {
        return goToLoginButton;
    }

    public By getNameInput(){
        return nameInput;
    }

    public By getEmailInput(){
        return emailInput;
    }

    public By getPasswordInput(){
        return passwordInput;
    }

    public By getRegisterButton(){
        return registerButton;
    }
}
