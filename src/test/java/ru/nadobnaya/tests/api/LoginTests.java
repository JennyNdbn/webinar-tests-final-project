package ru.nadobnaya.tests.api;


import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.nadobnaya.tests.api.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.nadobnaya.tests.api.specs.TestSpecs.*;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of login on events.webinar.ru")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Check response status of login")
    @Story("Testing of successful login using method POST")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("api"), @Tag("positive")})
    void checkLoginStatus() {
        step("Check that response status is 200", () -> {
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    //.cookie("sessionId=eecfd74d5e196f0975025f1bbee697f7; Expires=Tue, 25-Apr-2023 05:42:00 GMT; Max-Age=2419200; path=/; SameSite=None; secure; HttpOnly")
                    .body("email=Jennyqaguru%40gmail.com&password=123qweASD&rememberMe=true")
                    .when()
                    .post("/login")
                    .then()
                    .spec(testResponseSpecWithStatus);
        });
    }

    @Test
    @DisplayName("Check getting user information using cookie")
    @Story("Testing of successful login and getting user info using method GET")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("api"), @Tag("positive")})
    void checkGetLogin() {
        UserResponseModel response =
                step("Check that response status is 200", () ->
                        given(testRequestSpec)
                                .cookie("sessionId=28abdc7a8061b13a9b6d67724e6f8ec3; Path=/; Secure; HttpOnly; Expires=Tue, 25 Apr 2023 06:19:38 GMT;")
                                .when()
                                .get("/login")
                                .then()
                                .spec(testResponseSpecWithStatus)
                                .extract().as(UserResponseModel.class));
        step("Verify user data", () -> {
            assertThat(response.getId()).isEqualTo(71101593);
            assertThat(response.getEmail()).isEqualTo("jennyqaguru@gmail.com");
            assertThat(response.getNickname()).isEqualTo("Jenny Ndbn");
            assertThat(response.getName()).isEqualTo("Jenny");
            assertThat(response.getDefaultEventType()).isEqualTo("meeting");
        });
    }

    @Test
    @DisplayName("Check response status of login without cookie and body")
    @Story("Testing of unsuccessful login with empty body using method POST")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("api"), @Tag("negative")})
    void checkLoginStatusNegative() {
        ErrorResponseModel response =
                step("Try to login and check that response status is 418", () ->
                        given(testRequestSpec)
                                .body("")
                                .when()
                                .post("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(418)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("ERROR_MOBILE_USER"));
    }

    @Test
    @DisplayName("Check response status of user with wrong credentials")
    @Story("Testing of unsuccessful login with wrong credentials using method POST")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("api"), @Tag("negative")})
    void checkWrongLoginStatusNegative() {
        ErrorResponseModel response =
                step("Try to login and check that response status is 404", () ->
                        given(testRequestSpec)
                                .body("email=test%40gmail.com&password=test&rememberMe=true")
                                .when()
                                .post("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(404)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("Wrong credentials"));
    }

    @Test
    @DisplayName("Check response status of user without cookie")
    @Story("Testing of unsuccessful login and getting user info without the cookie using method GET")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("api"), @Tag("negative")})
    void checkUserNegative() {
        ErrorResponseModel response =
                step("Try to login and check that response status is 404", () ->
                        given(testRequestSpec)
                                .when()
                                .get("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(404)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("User is not found"));
    }

    @Test
    @DisplayName("Try using unallowed method DELETE in login")
    @Story("Testing of using unallowed method DELETE in login")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("api"), @Tag("negative")})
    void tryUsingUnallowedMethod() {
        ErrorResponseModel response =
                step("Try using DELETE and check that response status is 405", () ->
                        given(testRequestSpec)
                                .when()
                                .delete("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(405)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).contains("Method Not Allowed"));
    }

}
