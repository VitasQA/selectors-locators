package org.hw_0.selenium;

import org.hw_06.utils.ConfigReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTask1Test extends SeleniumBaseTest {

    @Test
    @DisplayName("1.1 Добавить товар через админку, выйти на витрину и проверить")
    void testAddProductViaAdmin() {
        driver.get(ConfigReader.getStandUrl() + "/login");
        driver.findElement(By.id("username")).sendKeys(ConfigReader.getAdminUsername());
        driver.findElement(By.id("password")).sendKeys(ConfigReader.getAdminPassword());
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        driver.findElement(By.id("n-name")).sendKeys(ConfigReader.getProductName());
        driver.findElement(By.id("n-price")).sendKeys(String.valueOf(ConfigReader.getProductPrice()));
        driver.findElement(By.id("add-btn")).click();
        driver.get(ConfigReader.getStandUrl());
        assertTrue(driver.findElement(By.xpath("//h4[text()='" + ConfigReader.getProductName() + "']")).isDisplayed(),
                "Товар должен быть на витрине");
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
        driver.get(ConfigReader.getStandUrl() + "/login");
        driver.findElement(By.id("username")).sendKeys("wrong_user");
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