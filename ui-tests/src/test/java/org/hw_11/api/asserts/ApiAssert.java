package org.hw_11.asserts;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.hw_11.api.BasicApi;
import org.hw_11.api.RestApiBuilder;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;

public class ApiAssert {
    @Step("Проверить, что товар с ID {id} существует")
    public static void assertProductExists(String id, String expectedName) {
        Response response = given()
                .spec(RestApiBuilder.withAuth(BasicApi.loginAndGetSession()))
                .get("/goods/list");
        assertThat(response.jsonPath().getString("data.find { it.id == " + id + " }.name"))
                .isEqualTo(expectedName);
    }
}