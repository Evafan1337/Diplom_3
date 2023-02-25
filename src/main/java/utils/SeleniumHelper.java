package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumHelper {

    @Step("Клик по HTML-элементу")
    public static void clickOnElem(WebDriver driver, By locator){
        driver.findElement(locator).click();
    }
}
