package org.hw_09.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.hw_09.utils.ConfigReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTask2Test extends SelenideBaseTest {

    @Test
    @DisplayName("2.1 Добавить товар через админку и проверить на витрине")
    void testAddProductViaAdmin() {
        open("/login");
        $("#username").setValue(ConfigReader.getAdminUsername());
        $("#password").setValue(ConfigReader.getAdminPassword());
        $("button[type='submit']").click();

        $("#n-name").setValue(ConfigReader.getProductName());
        $("#n-price").setValue(String.valueOf(ConfigReader.getProductPrice()));
        $("#add-btn").click();

        open("/");
        $(".product-card[data-name='" + ConfigReader.getProductName() + "']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("2.2 Добавить товар в корзину и проверить")
    void testAddToCart() {
        $(".product-card .btn").click();
        $("#open-cart-btn").click();
        $(".cart-item").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("2.3 Войти с неверным логином и паролем")
    void testLoginWithWrongCredentials() {
        open("/login");
        $("#username").setValue("wrong_user");
        $("#password").setValue("wrong_password");
        $("button[type='submit']").click();
        $("h2").shouldHave(Condition.text("Please sign in"));
    }

    @Test
    @DisplayName("2.4 Проверить сохранение товаров после обновления")
    void testCartPersistence() {
        $(".product-card .btn").click();
        Selenide.refresh();
        $("#open-cart-btn").click();
        $(".cart-item").shouldNotBe(Condition.visible);
    }

    @Test
    @DisplayName("2.5 Добавить товаров > 300 руб и проверить JS Alert")
    void testAlertWhenOrderOver300() {
        for (int i = 0; i < 7; i++) {
            $$(".product-card .btn").get(2).click();
        }
        $("#open-cart-btn").click();
        $("#makeOrder").click();

        String alertText = Selenide.switchTo().alert().getText();
        System.out.println("Alert: " + alertText);
        Selenide.switchTo().alert().accept();
    }
}