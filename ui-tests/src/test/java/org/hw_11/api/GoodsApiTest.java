package org.hw_11.api;

import io.qameta.allure.Step;
import org.hw_11.api.BasicApi;
import org.hw_11.api.RestApiBuilder;
import org.hw_11.asserts.ApiAssert;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.BDDAssumptions.given;

public class GoodsApiTest extends BasicApi {

    private static String createdId;

    @Test
    @DisplayName("API: Создание товара")
    void testCreateProduct() {
        String name = "API Товар " + System.currentTimeMillis();
        createdId = given()
                .spec(RestApiBuilder.withAuth(loginAndGetSession()))
                .body("{\"name\":\"" + name + "\",\"price\":100}")
                .post("/goods/add")
                .then()
                .extract().path("data.id").toString();

        ApiAssert.assertProductExists(createdId, name);
    }

    @AfterEach
    void tearDown() {
        if (createdId != null) {
            given()
                    .spec(RestApiBuilder.withAuth(loginAndGetSession()))
                    .delete("/goods/" + createdId)
                    .then()
                    .statusCode(200);
            createdId = null;
        }
    }
}