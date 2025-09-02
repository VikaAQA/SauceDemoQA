package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProductsPage extends BasePage {
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final By TITLE = By.className("title");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавление товаров{products} в корзину")
    public ProductsPage addProduct(String... products) {
        log.info(" Добаление {} в корзину", products);
        for (String product : products) {
        }
        return this;
    }

    public ProductsPage open() {
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    @Step("Открыта страница Каталога")
    public ProductsPage isPageOpened() {
        waitElement(TITLE);
        log.info("Открыта страница Каталога");
        return this;
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }


    @Step("Добавление в коризну нескольких товаров из каталога")
    //Добавить в корзину несколько товаров по индексу
    public void addFewProductInBasket(int... indexes) {
        for (int index : indexes) {
            String productName = getProductNameFromProduct(index);
            log.info(" Добаление {} в корзину", productName);
            driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, productName))).click();
        }
    }

    public String getProductNameFromProduct(int index) {
        String name = driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
        log.info("Получено имя товара по индексу {}: {}", index, name);
        return name;
    }

    @Step("Получение цены товара по имени из каталога")
    public String getProductPriceFromCatalog(String productName) {
        String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//div[@data-test='inventory-item-price']", productName);
        String price = driver.findElement(By.xpath(xpath)).getText();
        log.info("Получена цена товара '{}' из каталога: {}", productName, price);
        return price;
    }

    public String getProductPriceFromCatalogIndex(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_price"))
                .get(index)
                .getText();
    }
}