package org.hw_06.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTask1Test extends SeleniumBaseTest {

    @Test
    @DisplayName("1.1 Добавить товар через админку, выйти на витрину и проверить")
    void testAddProductViaAdmin() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("secret123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.findElement(By.id("n-name")).sendKeys("Помидоры");
        driver.findElement(By.id("n-price")).sendKeys("15");
        driver.findElement(By.id("add-btn")).click();

        driver.get("http://localhost:8080");
        assertTrue(driver.findElement(By.xpath("//h4[text()='Помидоры']")).isDisplayed(), "Товар должен быть на витрине");
    }

    @Test
    @DisplayName("1.2 Добавить товар в корзину и проверить")
    void testAddProductToCart() {
        driver.findElement(By.cssSelector(".product-card .btn")).click();
        driver.findElement(By.id("open-cart-btn")).click();
        assertTrue(driver.findElement(By.cssSelector(".cart-item")).isDisplayed(), "Товар должен быть в корзине");
    }

    @Test
    @DisplayName("1.3 Войти с неверным логином и паролем")
    void testLoginWithWrongCredentials() {
        driver.get("http://localhost:8080/login");
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("wrong_password");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        assertTrue(driver.getCurrentUrl().contains("/login"), "Должны остаться на странице логина");
    }

    @Test
    @DisplayName("1.4 Проверить сохранение товаров в корзине после обновления")
    void testCartPersistenceAfterRefresh() {
        driver.findElement(By.cssSelector(".product-card .btn")).click();
        driver.navigate().refresh();
        driver.findElement(By.id("open-cart-btn")).click();
        assertTrue(driver.findElements(By.cssSelector(".cart-item")).isEmpty(),
                "Корзина должна быть пуста после обновления");
    }
}