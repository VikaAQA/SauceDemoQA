package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    private final String TITLE_CHECKOUT = "//span[text()='Checkout: Your Information']";

    public CheckoutPage isPageOpened() {
        waitElement(By.xpath(TITLE_CHECKOUT));
        log.info("Страница CheckoutPage открыта");
        return this;
    }
 }
