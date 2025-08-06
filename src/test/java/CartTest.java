import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CartTest extends BaseTest {

    @Test
    public void checkCart() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String nameProductCatalog = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText();
        String priceProductCatalog = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']" +
                "/ancestor::div[@class='inventory_item']//div[@data-test='inventory-item-price']")).getText();

        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();

        String priceShoppingBasket = driver.findElement(By.cssSelector(".inventory_item_price")).getText();
        String nameProductShoppingBasket = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(priceShoppingBasket, priceProductCatalog, "Цена продукта  в каталоге и в корзине не совпадает ");
        softAssert.assertEquals(nameProductCatalog, nameProductShoppingBasket, "Имя продукта в корзине и в каталоге не совпадает");
        softAssert.assertAll();
    }
}
