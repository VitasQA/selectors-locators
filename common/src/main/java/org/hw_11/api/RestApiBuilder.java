package org.hw_11.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hw_09.utils.ConfigReader;

public class RestApiBuilder {
    private static RequestSpecification spec;

    public static RequestSpecification getSpec() {
        if (spec == null) {
            spec = new RequestSpecBuilder()
                    .setBaseUri(ConfigReader.getApiUrl())
                    .setContentType(ContentType.JSON)
                    .addFilter(new AllureRestAssured())
                    .build();
        }
        return spec;
    }

    public static RequestSpecification withAuth(String sessionId) {
        return RestAssured.given()
                .spec(getSpec())
                .cookie("JSESSIONID", sessionId);
    }
}