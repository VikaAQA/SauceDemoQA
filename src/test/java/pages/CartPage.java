package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private final String BTN_CHECKOUT = "//button[text()='Checkout']";
    private final String NAME_PRODUCT_PATTERN = "//*[@class='cart_item']//*[text()='%s']";
    private final String TITLE_PRODUCT = "//span[text()='Products']";
    private final String TITLE_SHOPPING = "//button[text()='Continue Shopping']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage openBasket() {
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    @Step("Страница корзины открыта")
    public CartPage isPageOpened() {
        waitElement(By.xpath(BTN_CHECKOUT));
        log.info("Страница корзины открыта");
        return this;
    }

    @Step("Получение цены товара в корзине")
    public String getProductPriceInBasket() {
    String price =  driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        log.info(" Цена товара в корзине {}",price);
        return price ;
    }

    @Step("Удаление продукта {product} из Корзины")
    public CartPage removeProductInBasket(String product) {
        log.info(" Удален продукт {} из Корзины", product);
        driver.findElement(By.xpath("//div[text()='" + product + "']/ancestor::div[@class='cart_item']//button[text()='Remove']")).click();
         return this;
    }


    @Step("Проверка количества товара в корзине")
    public int checkCountProductBasket() {
        int countProduct = driver.findElements(By.cssSelector(".cart_item")).size();
        log.info(" В корзине количество товара {}", countProduct);
        return countProduct;
    }

    @Step("Переход из корзины на страницу Оплаты")
    public CheckoutPage clickToCheckoutPage() {
        log.info("Осуществлен переход из корзины на страницу оплаты ");
        driver.findElement(By.xpath(BTN_CHECKOUT)).click();
        return new CheckoutPage(driver);
    }
    @Step("Нажатие на 'Продолжить покупки'")
    public ProductsPage clickToContinueShopping() {
        driver.findElement(By.xpath(TITLE_SHOPPING)).click();
        waitElement(By.xpath(TITLE_PRODUCT));
        log.info("Страница ProductsPage успешно открыта после перехода из CartPage");
        return new ProductsPage(driver);
    }

    //Проверка наличия товара в корзине
    public boolean isProductInCart(String product) {
        return driver.findElement(By.xpath(String.format(NAME_PRODUCT_PATTERN, product))).isDisplayed();//создаёт строку по заданному шаблону, подставляя в неё значения переменных.
    }

    //Проверка наличия товара по индексу
    public String getProductNameFromCart(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_name"))//Ищет все элементы по локатору
                .get(index)//берем номер товара
                .getText();//получаем название товара
    }

    //Проверка наличия товара по списку
    public ArrayList<String> getProductsName() {
        //  Находим все элементы с названиями товаров
        List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        // Создаём пустой список для хранения названий
        ArrayList<String> names = new ArrayList<>();
        //  Перебираем элементы и извлекаем текст
        for (WebElement product : allProductsElements) {
            names.add(product.getText());
        }
        //  Возвращаем список названий
        return names;
    }
}







