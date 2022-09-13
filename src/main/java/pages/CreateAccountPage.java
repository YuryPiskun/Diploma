package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateAccountPage extends BasePage {
    private final By manButton = By.id("id_gender1");
    private final By firstNameField = By.id("customer_firstname");
    private final By lastNameField = By.id("customer_lastname");
    private final By passwordField = By.id("passwd");
    private final By yearDropdown = By.id("cuselFrame-years");
    private final By registerButton = By.xpath("//span[contains(text(),'Register')]");
    private final By errorMessageField = By.cssSelector(".alert.alert-danger");

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    Actions action = new Actions(driver);

    public void selectManButton() {
        driver.findElement(manButton).click();
    }

    public void setFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void setYear(String year) {
        driver.findElement(yearDropdown).click();
        WebElement yearSelect = driver.findElement(By.xpath(String.format("//span[@val='%s']", year)));
        action.moveToElement(yearSelect).build().perform();
        yearSelect.click();
    }

    public void setDay(String day) {
        String openDropdownXpath = String.format("//div[@id='%s']", "uniform-days");
        driver.findElement(By.xpath(openDropdownXpath)).click();
        driver.findElement(By.xpath(String.format(openDropdownXpath + "//option[@value='%s']", day))).click();
    }

    public void setMonth(String month) {
        String openDropdownXpath = String.format("//div[@id='%s']", "uniform-months");
        driver.findElement(By.xpath(openDropdownXpath)).click();
        driver.findElement(By.xpath(String.format(openDropdownXpath + "//option[@value='%s']", month))).click();
    }

    public WebElement getRegisterButton() {
        return driver.findElement(registerButton);
    }

    public void clickRegister() {
        getRegisterButton().click();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(errorMessageField);
    }
}
