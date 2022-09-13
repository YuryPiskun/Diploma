package steps;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CreateAccountPage;
import pages.LoginPage;
import pages.MainPage;

public class CreateAccountStep extends BaseStep {

    public CreateAccountStep(WebDriver driver) {
        super(driver);
    }

    Faker faker = new Faker();

    @Step("Go to creating an account")
    public void goToCreatingAnAccountStep() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickLogin();
        loginPage.setEmail();
        loginPage.clickCreateAccount();
    }

    @Step("Filling in the data for creating a man account")
    public void fillDataAccountStep() {

        CreateAccountPage createAnAccountPage = new CreateAccountPage(driver);

        createAnAccountPage.selectManButton();
        createAnAccountPage.setFirstName(faker.bothify("??????"));
        createAnAccountPage.setLastName(faker.bothify("??????"));
        createAnAccountPage.setPassword("pppppp55555");
        createAnAccountPage.setDay("12");
        createAnAccountPage.setMonth("4");
        createAnAccountPage.setYear("1961");
    }

    @Step("Filling data for creating account without first name")
    public void fillDataAccountWithoutFirstNameStep() {

        CreateAccountPage createAnAccountPage = new CreateAccountPage(driver);

        createAnAccountPage.selectManButton();
        createAnAccountPage.setLastName(faker.bothify("??????"));
        createAnAccountPage.setPassword("pppppp55555");
        createAnAccountPage.setDay("12");
        createAnAccountPage.setMonth("4");
        createAnAccountPage.setYear("1961");
    }
}
