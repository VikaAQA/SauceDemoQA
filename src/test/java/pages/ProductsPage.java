package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {

     private final By TITLE = By.className("title");
     private final String ADD_TO_CART_PATTERN = "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";

     public ProductsPage(WebDriver driver) {
          super(driver);
     }

     public void open(){
          driver.get(BASE_URL+"inventory.html");
     }

      public ProductsPage isPageOpened(){//проверка что страница открыта
           waitElement(TITLE);
          return this;
   }

     public String getTitle() {
          return driver.findElement(TITLE).getText();
     }

     //Добавить в корзину товар по имени из каталога
     public void addProductInBasket(String... products) {
          for (String product : products) {
               driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
          }
     }

    //Добавить в корзину несколько товаров по индексу
    public void addFewProductInBasket(int... indexes) {
        for (int index : indexes) {
            String productName = getProductNameFromProduct(index);
            driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, productName))).click();
        }
    }

    public String getProductNameFromProduct(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_name"))
                .get(index)
                .getText();
    }

        // Получить цену товара по имени из каталога
     public String getProductPriceFromCatalog(String productName) {
          String xpath = String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//div[@data-test='inventory-item-price']", productName);
          return driver.findElement(By.xpath(xpath)).getText();
     }

    public String getProductPriceFromCatalogIndex(int index) {
        return driver.findElements(By.cssSelector(".inventory_item_price"))
                .get(index)
                .getText();
    }
}