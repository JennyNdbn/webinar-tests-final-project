package ru.nadobnaya.tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://events.webinar.ru";
        RestAssured.basePath = "/api";
    }
}
