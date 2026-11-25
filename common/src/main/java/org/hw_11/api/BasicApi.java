package org.hw_11.api;

import io.restassured.response.Response;
import io.qameta.allure.Step;
import org.hw_09.utils.ConfigReader;

import static io.restassured.RestAssured.given;

public abstract class BasicApi {
    protected static String sessionId;

    @Step("Получить сессию (JSESSIONID) через API")
    public static String loginAndGetSession() {
        if (sessionId == null) {
            Response response = given()
                    .spec(RestApiBuilder.getSpec())
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("username", ConfigReader.getAdminUsername())
                    .formParam("password", ConfigReader.getAdminPassword())
                    .redirects().follow(true)
                    .when()
                    .post("/login")
                    .then()
                    .extract().response();

            sessionId = response.getCookie("JSESSIONID");
            if (sessionId == null) {
                String setCookie = response.getHeader("Set-Cookie");
                if (setCookie != null && setCookie.contains("JSESSIONID=")) {
                    sessionId = setCookie.split("JSESSIONID=")[1].split(";")[0];
                }
            }
        }
        return sessionId;
    }
}