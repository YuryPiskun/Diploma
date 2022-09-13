package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertiesLoader;

public class MainPageTests extends BaseTest {

    @Test
    public void validateRedirectionSeventySaleBanner() {
        mainPage.open();
        mainPage.getSaleSeventyBanner().click();
        Assert.assertTrue(driver.getCurrentUrl().contains(PropertiesLoader.getUrl()));
    }

    @Test
    public void validateLanguageChange() {
        mainPage.open();
        mainPage.pickUpAnotherLanguage("Українська");
        Assert.assertEquals(mainPage.getCurrentPageLanguage(), "Українська");
    }

    @Test
    public void validateCurrencyChange() {
        mainPage.open();
        mainPage.pickUpAnotherCurrency("Гривна");
        Assert.assertEquals(mainPage.getCurrentCurrency(), "Гривна");
    }

    @Test
    public void validateRedirectionThreeDaysSaleBanner() {
        mainPage.open();
        mainPage.getThreeDaysSaleBanner().click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.prestashop.com/en");
    }

    @Test
    public void validateQuickViewPopup() {
        mainPage.open();
        mainPage.openProductQuickView("Faded Short Sleeve T-shirts");
        Assert.assertTrue(mainPage.getClosePopupButton().isDisplayed());
    }

    @Test
    public void validateNewsLettersWithValidEmail() {
        mainPage.open();
        mainPage.pickUpAnotherLanguage("English");
        mainPage.enterEmailToNewsLettersField(true, false);
        Assert.assertTrue(mainPage.getNotificationMessage().contains("You have successfully subscribed to this newsletter."));
    }

    @Test
    public void validateNewsLettersWithInvalidEmail() {
        mainPage.open();
        mainPage.pickUpAnotherLanguage("English");
        mainPage.enterEmailToNewsLettersField(false, false);
        Assert.assertTrue(mainPage.getNotificationMessage().contains("This email address is already registered."));
    }

    @Test
    public void validateNewsLettersWithEmptyEmail() {
        mainPage.open();
        mainPage.pickUpAnotherLanguage("English");
        mainPage.enterEmailToNewsLettersField(false, true);
        Assert.assertTrue(mainPage.getNotificationMessage().contains("Invalid email address."));
    }

    @Test
    public void validateOpenCategoryWomen() {
        mainPage.open();
        mainPage.openCategories("Women");
        Assert.assertTrue(mainPage.getCurrentCategoryName().contains("Women"));
    }

    @Test
    public void validateOpenCategoryDresses() {
        mainPage.open();
        mainPage.openCategories("Dresses");
        Assert.assertTrue(mainPage.getCurrentCategoryName().contains("Dresses"));
    }

    @Test
    public void validateOpenCategoryTShirts() {
        mainPage.open();
        mainPage.openCategories("T-shirts");
        Assert.assertTrue(mainPage.getCurrentCategoryName().contains("T-shirts"));
    }

    @Test
    public void validateSearchWithExistingItem() {
        mainPage.open();
        mainPage.enterTextInSearchFieldAndFind("Blouse");
        Assert.assertTrue(mainPage.countOfResults("Blouse") > 0);
    }

    @Test
    public void validateSearchWithNonExistingItem() {
        mainPage.open();
        mainPage.enterTextInSearchFieldAndFind("qwerty");
        Assert.assertTrue(mainPage.countOfResults("qwerty") < 1);
    }

    @Test
    public void validateSearchWithEmptyField() {
        mainPage.open();
        mainPage.enterTextInSearchFieldAndFind("");
        Assert.assertTrue(mainPage.countOfResults("") < 1);
    }
}
