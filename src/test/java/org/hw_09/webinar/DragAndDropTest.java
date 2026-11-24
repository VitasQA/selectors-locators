package org.hw_06.webinar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest extends WebinarBaseTest {

    @Test
    @DisplayName("1.1 Перетащить элемент в корзину с помощью Drag-and-Drop")
    void testDragAndDropToCart() {
        open("/");

        SelenideElement productCard = $(".product-card[data-name='Лук']");
        productCard.shouldBe(Condition.visible);

        SelenideElement cartButton = $("#open-cart-btn");
        cartButton.shouldBe(Condition.visible);

        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.dragAndDrop(productCard, cartButton).perform();

        $("#open-cart-btn").click();
        $(".cart-item").shouldBe(Condition.visible);
        $(".cart-item").shouldHave(Condition.text("Лук"));
    }

    @Test
    @DisplayName("1.2 Удалить добавленный элемент из корзины")
    void testRemoveItemFromCart() {
        open("/");

        $(".product-card[data-name='Лук'] .btn").click();
        $("#open-cart-btn").click();
        $(".cart-item").shouldBe(Condition.visible);

        $(".cart-item button[data-action='remove']").click();
        $(".cart-item").shouldNotBe(Condition.visible);
    }
}