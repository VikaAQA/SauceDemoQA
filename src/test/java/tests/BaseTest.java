package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import steps.ProductStep;

import java.time.Duration;
import java.util.HashMap;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    ProductStep productStep;

    protected final String USERNAME = "standard_user";
    protected final String PASSWORD = "secret_sauce";

    @Description("Авторизация пользователя")
    protected void loginAsStandardUser() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--headless");//для запуска драйвера браузера  на удаленном сервере
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        productStep = new ProductStep(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            AllureUtils.takeScreenshot(driver);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}
