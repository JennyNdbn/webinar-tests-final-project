package ru.nadobnaya.tests.api;


import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ru.nadobnaya.tests.api.models.ErrorResponseModel;
import ru.nadobnaya.tests.api.models.StatisticsResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.nadobnaya.tests.api.specs.TestSpecs.*;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of user statistics on events.webinar.ru")
public class UserStatisticsTests extends TestBase {

    @Test
    @DisplayName("Check total amount of user contacts")
    @Story("Testing of getting user statistics using method GET")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("api"), @Tag("positive")})
    void checkUserContacts() {
        StatisticsResponseModel response =
                step("Get user contacts using cookie", () ->
                        given(testRequestSpec)
                                .cookie("sessionId=28abdc7a8061b13a9b6d67724e6f8ec3; Path=/; Secure; HttpOnly; Expires=Tue, 25 Apr 2023 06:19:38 GMT;")
                                .when()
                                .get("/user/statistics")
                                .then()
                                .spec(testResponseSpecWithStatus)
                                .extract().as(StatisticsResponseModel.class));
        step("Verify single user data", () ->
                assertThat(response.getContacts().getTotal()).isEqualTo(0));
    }

    @Test
    @DisplayName("Check that method DELETE is forbidden for user statistics")
    @Story("Testing of deleting user statistics using method DELETE")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("api"), @Tag("negative")})
    void tryDeleteUserStatistics() {
        ErrorResponseModel response =
                step("Try using DELETE and check that response status is 403", () ->
                        given(testRequestSpec)
                                .cookie("sessionId=28abdc7a8061b13a9b6d67724e6f8ec3; Path=/; Secure; HttpOnly; Expires=Tue, 25 Apr 2023 06:19:38 GMT;")
                                .when()
                                .delete("/user/statistics")
                                .then()
                                .spec(testResponseSpecWithoutStatus)
                                .statusCode(403)
                                .extract().as(ErrorResponseModel.class));
        step("Verify error message", () ->
                assertThat(response.getError().getMessage()).isEqualTo("Forbidden"));
    }
}
