package steps;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;

@Log4j2
public class ProductStep {
    WebDriver driver;
    ProductsPage productsPage;

    public ProductStep(WebDriver driver) {
        this.driver = driver;
        productsPage = new ProductsPage(driver);
    }

    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

    @Step("Добавление в коризну товара из каталога") //Добавить в корзину товар по имени из каталога
    public void addProductInBasket(String... products) {
        productsPage.addProduct();
        log.info(" Добаление {} в корзину", products);
        for (String product : products) {
            driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        }
    }
}
