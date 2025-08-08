package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocator() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name"));
        driver.findElement(By.name("password"));
        driver.findElement(By.className("login_wrapper-inner"));
        driver.findElement(By.tagName("div"));

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.linkText("LinkedIn"));
        driver.findElement(By.partialLinkText("Link"));

        driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));//по атрибуту
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));//по тексту
        driver.findElement(By.xpath("//div[contains(text(), 'Backpack')]"));//по частичному тексту
        driver.findElement(By.xpath("//button[contains(@data-test, 'backpack')]"));//по частичному атрибуту
        driver.findElement(By.xpath("//span[text()='Products'][1]//ancestor::div[1]"));//поиск локатора вверх к родителю
        driver.findElement(By.xpath("//div[@id='shopping_cart_container']//descendant::a"));//поиск локатора вниз по DOM
        driver.findElement(By.xpath("//div[@class='inventory_item'][1]/following::div[2]"));
        driver.findElement(By.xpath("//span[@class='select_container']/parent::div"));
        driver.findElement(By.xpath("//span[@class='select_container']/preceding::div[1]"));
        driver.findElement(By.xpath("//div[@class='inventory_item' and @data-test='inventory-item'][1]"));

        driver.findElement(By.cssSelector(".shopping_cart_link"));//по классу
        driver.findElement(By.cssSelector("#contents_wrapper"));//по id
        driver.findElements(By.cssSelector(".btn_primary.btn_small")).get(0);//поиск по правилу .class1.class2
        driver.findElement(By.cssSelector(".inventory_item:first-of-type .pricebar .inventory_item_price"));//поиск по правилу .class1. class2
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']"));//по tagname
        driver.findElement(By.cssSelector("span[class='title']"));//[attribute=value]
        driver.findElement(By.cssSelector("footer.footer"));//по  tagname.class
        driver.findElement(By.cssSelector("[lang|='en']"));//по [attribute|=value]
        driver.findElement(By.cssSelector("a[href^='https']:nth-child(2)"));//по [attribute^=value]
        driver.findElement(By.cssSelector("[data-test$=twitter]"));//по [attribute$=value]
        driver.findElement(By.cssSelector("[data-test*=twitter]"));//по [attribute*=value]
        driver.findElements(By.cssSelector("[class~='btn_primary']")).get(0);//по [attribute~=value]
    }
}
