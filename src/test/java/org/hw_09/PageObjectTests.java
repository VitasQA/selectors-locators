package org.hw_09;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.hw_09.asserts.*;
import org.hw_09.pages.*;
import org.hw_09.utils.ConfigReader;
import org.hw_09.utils.TestDataGenerator;
import org.hw_09.webinar.WebinarBaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class PageObjectTests extends WebinarBaseTest {

    @Test
    @DisplayName("2.1 Добавить 3 единицы товара и оплатить (до 300 руб)")
    void testOrderThreeItems() {
        MainPage mainPage = new MainPage();
        MainPageAssert mainAssert = new MainPageAssert(mainPage);
        mainAssert.checkCartButtonVisible();

        mainPage.clickAddToCart();
        mainPage.clickAddToCart();
        mainPage.clickAddToCart();
        mainPage.openCart();

        CartPage cartPage = new CartPage();
        CartPageAssert cartAssert = new CartPageAssert(cartPage);
        cartAssert.checkQuantityOfFirstItem(3);

        cartPage.clickMakeOrder();
        checkToastVisible();
    }

    @Step("Проверить, что уведомление об успехе отображается")
    private void checkToastVisible() {
        $("#toast-container").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("2.2 Добавить несколько разных товаров и проверить общую цену")
    void testTotalPrice() {
        MainPage mainPage = new MainPage();
        MainPageAssert mainAssert = new MainPageAssert(mainPage);
        mainAssert.checkProductCardsNotEmpty();

        mainPage.clickAddToCartByIndex(0);
        mainPage.clickAddToCartByIndex(1);
        mainPage.openCart();

        CartPage cartPage = new CartPage();
        CartPageAssert cartAssert = new CartPageAssert(cartPage);
        cartAssert.checkCartNotEmpty();
        cartAssert.checkTotalPriceGreaterThanZero();
    }

    @Test
    @DisplayName("2.3 Войти в админку и добавить товар")
    void testAddProduct() {
        MainPage mainPage = new MainPage();
        mainPage.clickAdminButton();

        LoginPage loginPage = new LoginPage();
        LoginPageAssert loginAssert = new LoginPageAssert(loginPage);
        loginAssert.checkUsernameVisible();
        loginPage.login(ConfigReader.getAdminUsername(), ConfigReader.getAdminPassword());

        AdminPage adminPage = new AdminPage();
        String productName = TestDataGenerator.getRandomProductName();
        adminPage.enterProductName(productName);
        adminPage.enterProductPrice("100");
        adminPage.clickAddButton();

        AdminPageAssert adminAssert = new AdminPageAssert(adminPage);
        adminAssert.checkProductAddedToTable(productName);

        createdProductName = productName;
    }

    @Test
    @DisplayName("2.4 Войти в админку и отредактировать товар")
    void testEditProduct() {
        MainPage mainPage = new MainPage();
        mainPage.clickAdminButton();

        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getAdminUsername(), ConfigReader.getAdminPassword());

        AdminPage adminPage = new AdminPage();
        adminPage.editFirstProductPrice("35");

        open("/");
        MainPage updatedMainPage = new MainPage();
        MainPageAssert updatedMainAssert = new MainPageAssert(updatedMainPage);
        updatedMainAssert.checkProductCardsNotEmpty();
    }
}