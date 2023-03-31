package ru.nadobnaya.tests.mobile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
public class MobileLoginScreenTest extends TestBase {

    @Tag("mobile")
    @DisplayName("Logging in with correct user data")
    @Test
    void successfulLogInTest() {
        step("Press \"I already have an account\" button", () -> {
            $(id(iHaveAccountButton)).click();
        });
        step("Set user email, password and log in", () -> {
            $(id(etEmail)).sendKeys("jennyqaguru@gmail.com");
            $(id(etPassword)).sendKeys("123qweASD");
            $(id(logInButton)).click();
        });
        step("Verify that log in is successful", () -> {
            $(id(startNewMeetingButton)).shouldHave(text("NEW MEETING"));
            $(id(eventList)).shouldHave(text("There are no webinars scheduled for that day"));
        });
    }

    @Tag("mobile")
    @DisplayName("Testing of a button to log in with SSO")
    @Test
    void logInWithSSOButtonTest() {
        step("Press \"I already have an account\" button", () -> {
            $(id(iHaveAccountButton)).click();
        });
        step("Select log in with SSO", () -> {
            $(id(signInSSO)).click();
        });
        step("Verify that screen changed to log in with SSO", () -> {
            $(id(title)).shouldHave(text("Log in with SSO"));
        });
    }

    @Tag("mobile")
    @DisplayName("Testing of a button to recovery the password")
    @Test
    void forgotPasswordButtonTest() {
        step("Press \"I already have an account\" button", () -> {
            $(id(iHaveAccountButton)).click();
        });
        step("Set user email", () -> {
            $(id(etEmail)).sendKeys(testData.testEmail);
        });
        step("Press \"Forgot the password\" button", () -> {
            $(id(forgotPasswordButton)).click();
        });
        step("Verify that user email is already set and the same", () -> {
            $(id(etEmail)).shouldHave(text(testData.testEmail));
        });
    }
}
