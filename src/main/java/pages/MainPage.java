package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertiesLoader;

import java.util.List;

public class MainPage extends BasePage {

    private final By closePopupButton = By.xpath("//a[@title='Close']");
    private final By threeDaysSaleBanner = By.xpath("//img[contains(@src,'banner-img6')]");
    private final By saleSeventyBanner = By.xpath("//img[contains(@src,'sale70')]");
    private final By languageDropDown = By.xpath("//div[@id='languages-block-top']//div[@class='current']//span");
    private final By currencyDropDown = By.xpath("//span[@class='cur-label']");
    private final By submitNewsLettersButton = By.xpath("//button[@name='submitNewsletter']");
    private final By submitSearchButton = By.xpath("//button[@name='submit_search']");
    private final By loginButton = By.className("login");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(PropertiesLoader.getUrl());
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void openProductQuickView(String productName) {
        String productXpath = String.format("//ul[@id='homefeatured']//a[@title='%s' and @class='product-name']", productName);
        String quickViewButtonXpath = productXpath + "/ancestor::div[@class='product-container']//a[@class='quick-view']";
        WebElement necessaryProduct = driver.findElement(By.xpath(productXpath));
        actions.moveToElement(necessaryProduct).build().perform();
        WebElement quickViewButton = driver.findElement(By.xpath(quickViewButtonXpath));
        quickViewButton.click();
    }

    public void pickUpAnotherLanguage(String necessaryLanguage) {
        getLanguageDropdown().click();
        String necessaryLanguageXpath = String.format("//ul[@id='first-languages']//span[text()='%s']", necessaryLanguage);
        driver.findElement(By.xpath(necessaryLanguageXpath)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(necessaryLanguageXpath)));
    }

    public void pickUpAnotherCurrency(String necessaryCurrency) {
        getCurrencyDropdown().click();
        String necessaryCurrencyXpath = String.format("//ul[@id='first-currencies']//a[@title='%s']", necessaryCurrency);
        driver.findElement(By.xpath(necessaryCurrencyXpath)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(necessaryCurrencyXpath)));
    }

    public String getCurrentPageLanguage() {
        WebElement cartLabel = driver.findElement(By.xpath("//div[@class='shopping_cart']/child::a"));
        String currentLanguageCartName = cartLabel.getAttribute("title");
        if (currentLanguageCartName.contains("View my shopping cart")) {
            return "English";
        } else if (currentLanguageCartName.contains("Перейти до кошика")) {
            return "Українська";
        } else {
            return "Русский";
        }
    }

    public String getCurrentCurrency() {
        List<WebElement> productsList = driver.findElements(By.xpath("//span[@itemprop='price']"));
        String currentPriceLabel = productsList.get(0).getText();
        if (currentPriceLabel.contains("€")) {
            return "Евро";
        } else if (currentPriceLabel.contains("$")) {
            return "Доллар";
        } else {
            return "Гривна";
        }
    }

    public void enterEmailToNewsLettersField(boolean validEmail, boolean emptyField) {
        Faker faker = new Faker();
        String generatedEmail;
        WebElement newsLettersField = driver.findElement(By.xpath("//*[@id='newsletter-input']"));
        if (validEmail) {
            generatedEmail = faker.bothify("####?????@test.com");
        } else {
            generatedEmail = "qwerty@gmail.com";
        }
        if (emptyField) {
            getSubmitNewsLettersButton().click();
        } else {
            newsLettersField.sendKeys(generatedEmail);
            getSubmitNewsLettersButton().click();
        }
    }

    public String getNotificationMessage() {
        String notificationMessageXpath = "//p[contains(@class,'alert')]";
        WebElement notificationMessage = driver.findElement(By.xpath(notificationMessageXpath));
        wait.until(ExpectedConditions.visibilityOf(notificationMessage));
        return notificationMessage.getText();
    }

    public void enterTextInSearchFieldAndFind(String textToSearch) {
        WebElement searchField = driver.findElement(By.xpath("//input[@id='search_query_top']"));
        searchField.sendKeys(textToSearch);
        getSubmitSearchButton().click();
    }

    public int countOfResults(String textToSearch) {
        String resultsProductsXpath = String
                .format("//div[@id='center_column']//a[@class='product-name' and contains(@title,'%s')]", textToSearch);
        List<WebElement> resultsProducts = driver.findElements(By.xpath(resultsProductsXpath));
        return resultsProducts.size();
    }

    public void openCategories(String categoryName) {
        String categoryNameXpath = String.format("(//a[@title='%s'])[last()]", categoryName);
        driver.findElement(By.xpath(categoryNameXpath)).click();
    }

    public String getCurrentCategoryName() {
        return driver.findElement(By.xpath("//span[@class='category-name']")).getText();
    }

    public WebElement getClosePopupButton() {
        return driver.findElement(closePopupButton);
    }

    public WebElement getThreeDaysSaleBanner() {
        return driver.findElement(threeDaysSaleBanner);
    }

    public WebElement getSaleSeventyBanner() {
        return driver.findElement(saleSeventyBanner);
    }

    public WebElement getLanguageDropdown() {
        return driver.findElement(languageDropDown);
    }

    public WebElement getCurrencyDropdown() {
        return driver.findElement(currencyDropDown);
    }

    public WebElement getSubmitNewsLettersButton() {
        return driver.findElement(submitNewsLettersButton);
    }

    public WebElement getSubmitSearchButton() {
        return driver.findElement(submitSearchButton);
    }
}