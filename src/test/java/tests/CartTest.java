package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private static final String PRODUCT_NAME_BIKE_LIGHT = "Sauce Labs Bike Light";
    private static final String PRODUCT_NAME_FLEECE_JACKET = "Sauce Labs Fleece Jacket";

    @Test(groups = {"smoke"}, description = "Проверка соответствия цены товара в каталоге и корзине ", priority = 1)
    public void checkPriceFromProductInBasket() {
        loginAsStandardUser();
        productsPage.addProductInBasket(PRODUCT_NAME_BIKE_LIGHT);
        String priceInCataloge = productsPage.getProductPriceFromCatalog(PRODUCT_NAME_BIKE_LIGHT);
        cartPage.openBasket();
        cartPage.isPageOpened();
        Assert.assertEquals(priceInCataloge, cartPage.getProductPriceInBasket(), "Цена товара в корзине и каталоге не совпадает");
    }

    @Test(priority = 1,
            description = "Проверка добавления нескольких товаров в корзину")
    public void checkAddingFewProductInBasket() {
        loginAsStandardUser();
        productsPage.addFewProductInBasket(1, 2, 3, 5);
        cartPage.openBasket()
                .isPageOpened();
        Assert.assertEquals(cartPage.checkCountProductBasket(), 4, "Не совпадает количество добавленного товара в корзину");
    }

    @Test(description = "Проверка количества товара после удаления из корзины", priority = 2)
    public void checkRemoveProductInBasket() {
        loginAsStandardUser();
        productsPage.addProductInBasket(PRODUCT_NAME_BIKE_LIGHT, PRODUCT_NAME_FLEECE_JACKET);
        cartPage.openBasket()
                .isPageOpened()
                .removeProductInBasket(PRODUCT_NAME_FLEECE_JACKET);
        Assert.assertEquals(cartPage.checkCountProductBasket(), 1, "Не совпадает количество удалееного товара из коризны");
    }

    @Test(dependsOnMethods = "checkRemoveProductInBasket", priority = 3,
            description = "Проверка перехода на страницу оформления заказа")
    public void checkCheckoutPageFromBasket() {
        loginAsStandardUser();
        productsPage.addProductInBasket(PRODUCT_NAME_BIKE_LIGHT, PRODUCT_NAME_FLEECE_JACKET);
        cartPage.openBasket()
                .isPageOpened()
                .clickToCheckoutPage();
        checkoutPage.isPageOpened();
    }

    @Test(priority = 4, description = "Проверка продолжения покупок из корзины")
    public void checkContinueShoppingFromBasket() {
        loginAsStandardUser();
        productsPage.addFewProductInBasket(1, 2);
        cartPage.openBasket()
                .isPageOpened()
                .clickToContinueShopping();
        productsPage.isPageOpened();
        productsPage.addFewProductInBasket(3, 4);
        cartPage.openBasket()
                .isPageOpened();
        Assert.assertEquals(cartPage.checkCountProductBasket(), 4);
    }
}
