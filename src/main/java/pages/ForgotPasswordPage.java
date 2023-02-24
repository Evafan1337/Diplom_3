package pages;

import org.openqa.selenium.By;

public class ForgotPasswordPage extends BasePage{

    private By goToLoginButton = By.xpath("//*[contains(text(),'Войти')]");

    public By getGoToLoginButton() {
        return goToLoginButton;
    }

}
