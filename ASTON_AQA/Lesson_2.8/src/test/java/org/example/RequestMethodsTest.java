package org.example;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.nullValue;

public class RequestMethodsTest {
    @Test
    public void testGetRequest() {
        given()
                .baseUri("https://postman-echo.com")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
        .when()
                .get("/get")
        .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));

    }

    @Test
    public void testPostRawText() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testPostFormData() {
        given()
                .config(config().encoderConfig(
                        encoderConfig()
                                .appendDefaultContentCharsetToContentTypeIfUndefined(false)
                ))
                .baseUri("https://postman-echo.com")
                .contentType("application/x-www-form-urlencoded")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
        .when()
                .post("/post")
        .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));

    }

    @Test
    public void testPutRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
        .when()
                .put("/put")
        .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testPatchRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
        .when()
                .patch("/patch")
        .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }

    @Test
    public void testDeleteRequest() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .baseUri("https://postman-echo.com")
                .contentType("text/plain")
                .body(requestBody)
        .when()
                .delete("/delete")
        .then()
                .statusCode(200)
                .body("data", equalTo(requestBody));
    }


}
