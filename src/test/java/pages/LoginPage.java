package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage {
    private final By USERNAME_INPUT = By.id("user-name");
    private final By PASSWORD_INPUT = By.name("password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы авторизации")
    public LoginPage open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        log.info("Открыта Login Page");
        return this;
    }
    @Step("Авторизация пользователя {user}")
    public ProductsPage login(String user, String password) {
        log.info("Login in with cred{},{}", user, password);
        driver.findElement(USERNAME_INPUT).sendKeys(user);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }
    public String getErrorMessage() {
        String error = driver.findElement(ERROR_MESSAGE).getText();
        log.info("Получено сообщение об ошибке: {}", error);
        return error;
    }
}

