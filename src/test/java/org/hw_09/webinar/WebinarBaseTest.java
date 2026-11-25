package org.hw_09.webinar;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.hw_09.api.ProductApiClient;
import org.hw_09.utils.ConfigLogger;
import org.hw_09.utils.ConfigReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class WebinarBaseTest {
    protected String createdProductId;
    protected String createdProductName; // <-- НОВОЕ ПОЛЕ для имени товара

    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));
    }

    @BeforeEach
    public void setUp() {
        ConfigLogger.printConfig();
        RestAssured.baseURI = ConfigReader.getApiUrl();
        Configuration.browser = "chrome";
        Configuration.baseUrl = ConfigReader.getStandUrl();
        Configuration.timeout = ConfigReader.getTimeout();
        open("/");
    }

    @AfterEach
    @Step("Удалить созданный товар (ID: {createdProductId}, Имя: {createdProductName})")
    public void tearDown() {
        // 1. Если товар создан через API (есть ID) — удаляем по API
        if (createdProductId != null && !createdProductId.isEmpty()) {
            try {
                String sessionId = ProductApiClient.getSessionId();
                RestAssured.given()
                        .cookie("JSESSIONID", sessionId)
                        .header("Cookie", "JSESSIONID=" + sessionId)
                        .when()
                        .delete("/goods/" + createdProductId)
                        .then()
                        .statusCode(200);
            } catch (Exception e) {
                System.out.println("Не удалось удалить товар " + createdProductId + ": " + e.getMessage());
            }
        }

        // 2. Если товар создан через UI (есть имя, но нет ID) — удаляем через админку
        if (createdProductName != null && !createdProductName.isEmpty()) {
            try {
                open("/admin");
                // Ищем строку с нашим товаром и нажимаем "Удалить"
                $("tbody#tbody input[value='" + createdProductName + "']")
                        .closest("tr")
                        .$(".btn-del")
                        .click();
            } catch (Exception e) {
                System.out.println("Не удалось удалить товар по имени " + createdProductName + ": " + e.getMessage());
            }
        }

        Selenide.closeWebDriver();
    }
}