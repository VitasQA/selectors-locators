package org.hw_06.webinar.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hw_06.utils.ConfigReader;

import static io.restassured.RestAssured.given;

public class ProductApiClient {
    private static String sessionId;

    public static String loginAndGetSession() {
        RestAssured.baseURI = ConfigReader.getApiUrl();

        Response response = given()
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

        if (sessionId == null) {
            throw new RuntimeException("Не удалось получить JSESSIONID");
        }

        System.out.println("API JSESSIONID = " + sessionId);
        return sessionId;
    }

    public static String getSessionId() {
        if (sessionId == null) {
            loginAndGetSession();
        }
        return sessionId;
    }

    public static String createProduct(String name, int price) {
        RestAssured.baseURI = ConfigReader.getApiUrl();

        if (sessionId == null) {
            loginAndGetSession();
        }

        String jsonBody = "{\"name\": \"" + name + "\", \"price\": " + price + "}";

        Integer id = given()
                .contentType("application/json")
                .cookie("JSESSIONID", sessionId)
                .header("Cookie", "JSESSIONID=" + sessionId)
                .body(jsonBody)
                .when()
                .post("/goods/add")
                .then()
                .statusCode(200)
                .extract().path("data.id");

        return String.valueOf(id);
    }
}