package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final String BTN_CHECKOUT = "//button[text()='Checkout']";
    private final String NAME_PRODUCT_PATTERN = "//*[@class='cart_item']//*[text()='%s']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage openBasket() {
        driver.get(BASE_URL + "cart.html");
        return this;
    }

    public CartPage isPageOpened() {//Проверка, что страница открыта
        waitElement(By.xpath(BTN_CHECKOUT));
        return this;
    }

    public String getProductPriceInBasket() {
        return driver.findElement(By.cssSelector(".inventory_item_price")).getText();
    }

    public void removeProductInBasket(String product) {
        driver.findElement(By.xpath("//div[text()='" + product + "']/ancestor::div[@class='cart_item']//button[text()='Remove']")).click();
    }

    public int checkCountProductBasket() {
        return driver.findElements(By.cssSelector(".cart_item")).size();
    }

    public void clickToCheckoutPage() {
        driver.findElement(By.xpath(BTN_CHECKOUT)).click();
    }

    public void clickToContinueShopping() {
        driver.findElement(By.xpath("  //button[text()='Continue Shopping']")).click();
        waitElement(By.xpath("//span[text()='Products']"));//Ждет появления заголовка Продукт
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







