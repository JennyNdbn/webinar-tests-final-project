package ru.nadobnaya.tests.api;


import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.nadobnaya.tests.api.models.*;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.nadobnaya.tests.api.specs.TestSpecs.*;

public class WebinarTests extends TestBaseWebinar {

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of post login")
    @Tag("webinar")
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
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check get login request")
    @Tag("webinar")
    void checkGetLogin() {
        UserResponseModel response =
                step("Check that response status is 200", () ->
                        given(testRequestSpec)
                                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                                .cookie("sessionId=28abdc7a8061b13a9b6d67724e6f8ec3; Path=/; Secure; HttpOnly; Expires=Tue, 25 Apr 2023 06:19:38 GMT;")
                                .when()
                                .get("/login")
                                .then()
                                .spec(testResponseSpecWithStatus)
                                .extract().as(UserResponseModel.class));
        step("Verify single user data", () -> {
            assertThat(response.getId()).isEqualTo(71101593);
            assertThat(response.getEmail()).isEqualTo("jennyqaguru@gmail.com");
            assertThat(response.getNickname()).isEqualTo("Jenny Ndbn");
            assertThat(response.getName()).isEqualTo("Jenny");
            assertThat(response.getDefaultEventType()).isEqualTo("meeting");
        });
    }

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check total amount of user contacts")
    @Tag("webinar")
    void checkUserContacts() {
        StatisticsResponseModel response =
                step("Get user contacts using cookie", () ->
                        given(testRequestSpec)
                                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                                .cookie("sessionId=28abdc7a8061b13a9b6d67724e6f8ec3; Path=/; Secure; HttpOnly; Expires=Tue, 25 Apr 2023 06:19:38 GMT;")
                                .when()
                                .get("/user/statistics")
                                .then()
                                .spec(testResponseSpecWithStatus)
                                .extract().as(StatisticsResponseModel.class));
        step("Verify single user data", () -> {
            assertThat(response.getContacts().getTotal()).isEqualTo(0);
        });

    }

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of login without cookie and body")
    @Tag("webinar")
    void checkLoginStatusNegative() {
        ErrorResponseModel response =
                step("Check that response status is 418", () ->
                        given(testRequestSpec)
                                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                                .body("")
                                .when()
                                .post("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(418)
                                .extract().as(ErrorResponseModel.class));
        step("Verify single user data", () ->
                assertThat(response.getError().getMessage()).isEqualTo("ERROR_MOBILE_USER")
        );
    }

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of user with wrong credentials")
    @Tag("webinar")
    void checkWrongLoginStatusNegative() {
        ErrorResponseModel response =
                step("Check that response status is 404", () ->
                        given(testRequestSpec)
                                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                                .body("email=test%40gmail.com&password=test&rememberMe=true")
                                .when()
                                .post("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(404)
                                .extract().as(ErrorResponseModel.class));
        step("Verify single user data", () ->
                assertThat(response.getError().getMessage()).isEqualTo("Wrong credentials")
        );
    }

    @Test
    @Owner("Evgeniia Nadobnaia")
    @DisplayName("Check response status of user without cookie")
    @Tag("webinar")
    void checkUserNegative() {
        ErrorResponseModel response =
                step("Check that response status is 404", () ->
                        given(testRequestSpec)
                                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                                .when()
                                .get("/login")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(404)
                                .extract().as(ErrorResponseModel.class));
        step("Verify single user data", () ->
                assertThat(response.getError().getMessage()).isEqualTo("User is not found")
        );

    }
}
