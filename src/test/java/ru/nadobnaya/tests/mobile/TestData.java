package ru.nadobnaya.tests.mobile;

import com.github.javafaker.Faker;

public class TestData {

    Faker faker = new Faker();
    String testEmail = faker.internet().emailAddress();

}
