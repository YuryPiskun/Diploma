package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.AccountPage;
import pages.CreateAccountPage;
import pages.LoginPage;
import pages.MainPage;
import steps.CreateAccountStep;
import utils.PropertiesLoader;

import java.time.Duration;

public class BaseTest {
    public AccountPage accountPage;
    public CreateAccountPage createAccountPage;
    public CreateAccountStep createAccountStep;
    public LoginPage loginPage;
    public MainPage mainPage;
    protected WebDriver driver;
    WebDriverWait wait;

    @Parameters("browser")
    @BeforeMethod()
    public void setUp(String browser, ITestContext iTestContext) {
        if (browser.equals("chrome")) {
            //Initialize web driver and create driver instance
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            driver = new ChromeDriver(options);
        } else if (browser.equals("headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PropertiesLoader.getTimeout()));
        iTestContext.setAttribute("driver", driver);
        //Pages
        accountPage = new AccountPage(driver);
        createAccountPage = new CreateAccountPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        //Steps
        createAccountStep = new CreateAccountStep(driver);
    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
