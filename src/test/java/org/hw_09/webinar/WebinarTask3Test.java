package org.hw_06.webinar;

import com.codeborne.selenide.Condition;
import org.hw_06.utils.ConfigReader;
import org.hw_09.api.ProductApiClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebinarTask3Test extends WebinarBaseTest {

    @Test
    @DisplayName("3.1 Добавить три единицы товара и оплатить (до 300 руб)")
    void testOrderThreeItemsUnder300() {
        clearCart();

        for (int i = 0; i < 3; i++) {
            $(".product-card .btn").click();
        }
        $("#open-cart-btn").click();
        $("#makeOrder").click();

        $("#toast-container").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("3.2 Добавить несколько разных товаров и проверить общую цену")
    void testTotalPriceCalculation() {
        open("/");
        if ($(".cart-item").exists()) {
            $("#open-cart-btn").click();
            $$(".cart-item button[data-action='remove']").forEach(el -> {
                if (el.isDisplayed()) el.click();
            });
            if ($("#close-modal").exists()) $("#close-modal").click();
        }
        refresh();
        sleep(1000);

        $$(".product-card .btn").get(0).click();
        $$(".product-card .btn").get(1).click();
        $("#open-cart-btn").click();

        $$(".cart-item").shouldHave(com.codeborne.selenide.CollectionCondition.size(2));
        String totalText = $("#total-price").getText();
        int total = Integer.parseInt(totalText);
        assertTrue(total > 0, "Сумма в корзине должна быть больше 0");
    }

    @Test
    @DisplayName("3.3 Войти в админку и добавить товар. Проверить уведомление")
    void testAddProductViaAdmin() {
        open("/login");
        $("#username").setValue(ConfigReader.getAdminUsername());
        $("#password").setValue(ConfigReader.getAdminPassword());
        $("button[type='submit']").click();

        String productName = "Товар " + System.currentTimeMillis();
        $("#n-name").setValue(productName);
        $("#n-price").setValue("100");
        $("#add-btn").click();

        $("tbody#tbody input[value='" + productName + "']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("3.4 Войти в админку и отредактировать товар")
    void testEditProductViaAdmin() {
        open("/login");
        $("#username").setValue(ConfigReader.getAdminUsername());
        $("#password").setValue(ConfigReader.getAdminPassword());
        $("button[type='submit']").click();

        $("tbody#tbody tr:first-child td:nth-child(3) input").setValue("35");
        $("tbody#tbody tr:first-child .btn-upd").click();

        open("/");
        $(".product-card[data-name='Банан']").shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("3.5 Генерация тестовых данных через API")
    void testCreateProductViaApi() {
        String productName = "API Товар " + System.currentTimeMillis();
        createdProductId = ProductApiClient.createProduct(productName, 100);

        open("/");
        $(".product-card[data-name='" + productName + "']").shouldBe(Condition.visible);
    }

    private void clearCart() {
        if ($(".cart-item").exists()) {
            $("#open-cart-btn").click();
            $$(".cart-item button[data-action='remove']").forEach(el -> {
                if (el.isDisplayed()) el.click();
            });
            if ($("#close-modal").exists()) $("#close-modal").click();
            $(".cart-item").shouldNotBe(Condition.visible);
        }
    }
}