package pages;

import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Locale;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By authenticationField = By.className("page-heading");
    private final By createAccountField = By.id("email_create");
    private final By createAccountButton = By.cssSelector(".icon-user.left");

    FakeValuesService fakeValuesService = new FakeValuesService(
            new Locale("en-GB"), new RandomService());


    public String getAuthenticationField() {
        return driver.findElement(authenticationField).getText();
    }

    public void setEmail() {
        String email = fakeValuesService.bothify("??????####@mail.ru");
        driver.findElement(createAccountField).sendKeys(email);
    }

    public WebElement getCreateAccountButton() {
        return driver.findElement(createAccountButton);
    }

    public void clickCreateAccount() {
        getCreateAccountButton().click();
    }
}