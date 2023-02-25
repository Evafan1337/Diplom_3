package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By emailFieldInput = By.xpath("(//*[contains(text(),'Email')])/following-sibling::input");

    private By passwordFieldInput = By.xpath("(//*[contains(text(),'Пароль')])/following-sibling::input");

    private By loginBtn = By.xpath("(//*[contains(text(),'Войти')])");

    public By getLoginBtn() {
        return loginBtn;
    }

    public By getEmailFieldInput() {
        return emailFieldInput;
    }

    public By getPasswordFieldInput() {
        return passwordFieldInput;
    }
}
