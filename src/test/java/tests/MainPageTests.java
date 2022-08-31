package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    @Test
    public void validateQuickViewPopup() {
        mainPage.open();
        mainPage.openProductQuickView("Dress");
        Assert.assertTrue(mainPage.getClosePopupButton().isDisplayed());
    }
}
