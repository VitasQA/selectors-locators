package org.hw_09.utils;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.restassured.AllureRestAssured;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;

public class AllureConfig {
    public static void setup() {
        // 1. Листенер для Selenide (скриншоты, шаги)
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
                .includeSelenideSteps(true));

        // 2. Фильтр для RestAssured (логирование API-запросов)
        RestAssured.filters(new AllureRestAssured());
    }
}