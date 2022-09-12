package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class MainPageTests extends BaseTest {

    @Test
    public void validateQuickViewPopup() {
        mainPage.open();
/*
        mainPage.openProductQuickView("Faded Short Sleeve T-shirts");
        Assert.assertTrue(mainPage.getClosePopupButton().isDisplayed());
*/
    }
}
