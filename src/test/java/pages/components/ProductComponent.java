package pages.components;

import org.openqa.selenium.By;

public class ProductComponent {
    private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    public void addProduct(String... products) {
        for (String product : products) {
            //        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        }
    }
}
