package ru.nadobnaya.tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    String userCookie = "sessionId=28abdc7a8061b13a9b6d67724e6f8ec3; Path=/; Secure; HttpOnly; Expires=Tue, 25 Apr 2023 06:19:38 GMT;";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://events.webinar.ru";
        RestAssured.basePath = "/api";
    }
}
