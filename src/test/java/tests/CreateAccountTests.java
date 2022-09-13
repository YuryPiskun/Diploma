package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends BaseTest {
    @Test
    public void createAccount() {
        createAccountStep.goToCreatingAnAccountStep();
        createAccountStep.fillDataAccountStep();
        createAccountPage.clickRegister();
        Assert.assertEquals(accountPage.getSuccessfulMessage(), "Your account has been created.");
    }

    @Test
    public void registrationWithoutFirstName() {
        createAccountStep.goToCreatingAnAccountStep();
        createAccountStep.fillDataAccountWithoutFirstNameStep();
        createAccountPage.clickRegister();
        Assert.assertTrue(createAccountPage.getErrorMessage().isDisplayed());
    }
}
