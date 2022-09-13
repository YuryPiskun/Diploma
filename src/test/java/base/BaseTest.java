package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.MainPage;
import utils.PropertiesLoader;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    WebDriverWait wait;
    public MainPage mainPage;

    @BeforeMethod
    public void setUp(ITestContext iTestContext) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(PropertiesLoader.getTimeout()));
        iTestContext.setAttribute("driver", driver);
        //Pages
        mainPage = new MainPage(driver);
    }

    @AfterMethod(alwaysRun = true)

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
