package pages;

import org.openqa.selenium.By;

public class ProfilePage extends BasePage {

    private By logoutBtn = By.xpath("(//*[contains(text(),'Выход')])");

    public By getLogoutBtn() {
        return logoutBtn;
    }
}
