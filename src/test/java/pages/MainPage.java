package pages;

import org.openqa.selenium.By;

public class MainPage extends BasePage {
    private By goToAccountButton = By.xpath("//*[contains(text(),'Войти в аккаунт')]");

    private By bunsHeader = By.xpath("(//*[contains(@class,'tab_tab')])[1]");
    private By bunsConstructorHeader = By.xpath("(//*[contains(text(),'Булки')])[2]");

    private By sauseHeader = By.xpath("(//*[contains(@class,'tab_tab')])[2]");
    private By sauseConstructorHeader = By.xpath("(//*[contains(text(),'Соусы')])[2]");

    private By fillsHeader = By.xpath("(//*[contains(@class,'tab_tab')])[3]");
    private By fillsConstructorHeader = By.xpath("(//*[contains(text(),'Начинки')])[2]");
    private By feedPageBtn = By.xpath("(//*[contains(text(),'Лента Заказов')])");


    public By getFeedPageBtn() {
        return feedPageBtn;
    }

    public By getBunsConstructorHeader() {
        return bunsConstructorHeader;
    }

    public By getSauseConstructorHeader() {
        return sauseConstructorHeader;
    }

    public By getFillsConstructorHeader() {
        return fillsConstructorHeader;
    }

    public By getBunsHeader() {
        return bunsHeader;
    }

    public By getSauseHeader() {
        return sauseHeader;
    }

    public By getFillsHeader() {
        return fillsHeader;
    }

    public By getAccountButton() {
        return accountButton;
    }

    public By getGoToAccountButton() {
        return goToAccountButton;
    }
}
