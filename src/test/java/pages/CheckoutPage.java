package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage isPageOpened() {//проверка что страница открыта
        waitElement(By.xpath("//span[text()='Checkout: Your Information']"));//Ждет появления заголовка
        return this;//
    }
}
