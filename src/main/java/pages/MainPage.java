package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertiesLoader;

public class MainPage extends BasePage {

    private final By closePopupButton = By.xpath("//a[@title='Close']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(PropertiesLoader.getUrl());
    }

    public void openProductQuickView(String productName) {
        String productXpath = String.format("//*[@id='homefeatured']//*[@title='%s' and @class='product-name']",productName);
        String quickViewButtonXpath = productXpath + "/preceding::span[text()='Quick view'][1]";
        WebElement necessaryProduct = driver.findElement(By.xpath(productXpath));
        actions.moveToElement(necessaryProduct).build().perform();
        WebElement quickViewButton = driver.findElement(By.xpath(quickViewButtonXpath));
        quickViewButton.click();
    }

    public WebElement getClosePopupButton() {
        return driver.findElement(closePopupButton);
    }
}
