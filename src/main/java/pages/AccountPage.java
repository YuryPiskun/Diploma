package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    private final By successfulRegistrationField = By.cssSelector(".alert.alert-success");


    public String getSuccessfulMessage() {
        return driver.findElement(successfulRegistrationField).getText();
    }
}