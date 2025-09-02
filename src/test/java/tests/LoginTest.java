package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke"}, description = "Тест успешной авторизации стандартного пользователя",
            priority = 1)
    public void checkPositiveLogin() {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(productsPage.getTitle(), "Products",
                "Логин не выполнен");
    }

    @Test(description = "Тест авторизации без ввода пароля",
            priority = 2)
    public void checkWithEmptyPassword() {
        loginPage.open();
        loginPage.login(user, "");
        assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required",
                "Сообщение об ошибке не соответствует");
    }

    @Test(description = "Тест авторизации без ввода логина",
            priority = 3, retryAnalyzer = Retry.class)
    public void checkWithEmptyLogin() {
        loginPage.open();
        loginPage.login("", password);
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username is required",
                "Сообщение об ошибке не соответствует");
    }

    @Test(description = "Тест авторизации с неправильным логином и паролем",
            priority = 4)
    public void checkInvalidLoginPass() {
        loginPage.open();
        loginPage.login("test", "test");
        assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Сообщение об ошибке не соответствует");
    }

    @DataProvider(name = "Проверка логина с негативными данными ")//Передача тестовых данных
    public Object[][] loginData() {
        return new Object[][]{
                {"", password, "Epic sadface: Username is required"},
                {user, "", "Epic sadface: Password is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Проверка логина с негативными данными ", description = "Тест различных негативных сценариев авторизации",
            priority = 5)
    public void paramsWithEmptyPassword(String user, String password, String expectedErrorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.getErrorMessage(), expectedErrorMessage,
                "Сообщение об ошибке не соответствует");
    }//"Epic sadface: Password is required"
}
