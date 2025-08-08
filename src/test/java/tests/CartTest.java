package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private static final String PRODUCT_NAME_BIKE_LIGHT  = "Sauce Labs Bike Light";
    private static final String PRODUCT_NAME_FLEECE_JACKET  = "Sauce Labs Fleece Jacket";

    @Test
    public void checkPriceFromProductInBasket() {
        loginAsStandardUser();
        productsPage.addProductInBasket(PRODUCT_NAME_BIKE_LIGHT);
        String priceInCataloge = productsPage.getProductPriceFromCatalog(PRODUCT_NAME_BIKE_LIGHT);
        cartPage.openBasket();
        cartPage.isPageOpened();
        Assert.assertEquals(priceInCataloge, cartPage.getProductPriceInBasket(),"Цена товара в корзине и каталоге не совпадает");
    }

    @Test
    public void checkAddingFewProductInBasket() {
        loginAsStandardUser();
        productsPage.addFewProductInBasket(1, 2, 3, 5);
        cartPage.openBasket();
        cartPage.isPageOpened();
        Assert.assertEquals(cartPage.checkCountProductBasket(), 4, "Не совпадает количество добавленного товара в корзину");
    }

    @Test
    public void checkRemoveProductInBasket() {
        loginAsStandardUser();
        productsPage.addProductInBasket( PRODUCT_NAME_BIKE_LIGHT , PRODUCT_NAME_FLEECE_JACKET );//Подумать как получать список (лист товаров и их добавлять в корзину)
        cartPage.openBasket();
        cartPage.isPageOpened();
        cartPage.removeProductInBasket(PRODUCT_NAME_FLEECE_JACKET );
        Assert.assertEquals(cartPage.checkCountProductBasket(), 1, "Не совпадает количество удалееного товара из коризны");
    }

    @Test
    public void checkCheckoutPageFromBasket() {
        loginAsStandardUser();
        productsPage.addProductInBasket(PRODUCT_NAME_BIKE_LIGHT , PRODUCT_NAME_FLEECE_JACKET );
        cartPage.openBasket();
        cartPage.isPageOpened();
        cartPage.clickToCheckoutPage();
        checkoutPage.isPageOpened();
    }

    @Test
    public void checkContinueShoppingFromBasket() {
        loginAsStandardUser();
        productsPage.addFewProductInBasket(1, 2);
        cartPage.openBasket();
        cartPage.isPageOpened();
        cartPage.clickToContinueShopping();
        productsPage.isPageOpened();
        productsPage.addFewProductInBasket(3, 4);
        cartPage.openBasket();
        cartPage.isPageOpened();
        Assert.assertEquals(cartPage.checkCountProductBasket(), 4);
    }
}
