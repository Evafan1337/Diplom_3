package pages;

import org.openqa.selenium.By;

public class BasePage {

    public By accountButton = By.xpath("//*[contains(text(),'Личный Кабинет')]");
    public By constructorButton = By.xpath("(//*[contains(text(),'Конструктор')])");
    public By logoLink = By.xpath("//*[contains(@class,'AppHeader_header__logo__2D0X2')]/a");

    public By getConstructorButton() {
        return constructorButton;
    }

    public By getLogoLink() {
        return logoLink;
    }

    public By getAccountButton() {
        return accountButton;
    }
}
