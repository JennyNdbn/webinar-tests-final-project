package ru.nadobnaya.tests.api;


import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.nadobnaya.tests.api.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.nadobnaya.tests.api.specs.TestSpecs.*;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of login on events.webinar.ru")
@Tag("api")
public class LoginTests extends TestBase {

    @Test
    @DisplayName("Check response status of login")
    @Story("Testing of successful login using method POST")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("positive")
    void checkLoginStatus() {
        LoginBodyModel loginData = new LoginBodyModel();
        loginData.setEmail("Jennyqaguru@gmail.com");
        loginData.setPassword("123qweASD");
        loginData.setRememberMe(true);
        String loginBody = "email=" + loginData.getEmail() + "&password=" + loginData.getPassword() + "&rememberMe=" + loginData.getRememberMe();

        step("Check that response status is 200", () -> {
            given(testRequestSpec)
                    .contentType("application/x-www-form-urlencoded; charset=utf-8")
                    //.cookie("sessionId=eecfd74d5e196f0975025f1bbee697f7; Expires=Tue, 25-Apr-2023 05:42:00 GMT; Max-Age=2419200; path=/; SameSite=None; secure; HttpOnly")
                    .body(loginBody)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpecStatusCodeIs200);
        });
    }

    @Test
    @DisplayName("Check getting user information using cookie")
    @Story("Testing of successful login and getting user info using method GET")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("positive")
    void checkGetLogin() {
        UserResponseModel response =
                step("Check that response status is 200", () ->
                        given(testRequestSpec)
                                .cookie(userCookie)
                                .when()
                                .get("/login")
                                .then()
                                .spec(responseSpecStatusCodeIs200)
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
    @Tag("negative")
    void checkLoginStatusNegative() {
        ErrorResponseModel response =
                step("Try to login and check that response status is 418", () ->
                        given(testRequestSpec)
                                .body("")
                                .when()
                                .post("/login")
                                .then()
                                .spec(responseSpecWithoutStatus)
                                .statusCode(418)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("ERROR_MOBILE_USER"));
    }

    @Test
    @DisplayName("Check response status of user with wrong credentials")
    @Story("Testing of unsuccessful login with wrong credentials using method POST")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("negative")
    void checkWrongLoginStatusNegative() {
        Faker faker = new Faker();
        String testEmail = faker.internet().emailAddress();
        String testPassword = faker.internet().password(6, 10, true, true, true);

        LoginBodyModel loginData = new LoginBodyModel();
        loginData.setEmail(testEmail);
        loginData.setPassword(testPassword);
        loginData.setRememberMe(true);
        String loginBody = "email=" + loginData.getEmail() + "&password=" + loginData.getPassword() + "&rememberMe=" + loginData.getRememberMe();

        ErrorResponseModel response =
                step("Try to login and check that response status is 404", () ->
                        given(testRequestSpec)
                                .body(loginBody)
                                .when()
                                .post("/login")
                                .then()
                                .spec(responseSpecWithoutStatus)
                                .statusCode(404)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("Wrong credentials"));
    }

    @Test
    @DisplayName("Check response status of user without cookie")
    @Story("Testing of unsuccessful login and getting user info without the cookie using method GET")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("negative")
    void checkUserNegative() {
        ErrorResponseModel response =
                step("Try to login and check that response status is 404", () ->
                        given(testRequestSpec)
                                .when()
                                .get("/login")
                                .then()
                                .spec(responseSpecWithoutStatus)
                                .statusCode(404)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("User is not found"));
    }

    @Test
    @DisplayName("Try using unallowed method DELETE in login")
    @Story("Testing of using unallowed method DELETE in login")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("negative")
    void tryUsingUnallowedMethod() {
        ErrorResponseModel response =
                step("Try using DELETE and check that response status is 405", () ->
                        given(testRequestSpec)
                                .when()
                                .delete("/login")
                                .then()
                                .spec(responseSpecWithoutStatus)
                                .statusCode(405)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).contains("Method Not Allowed"));
    }

}
