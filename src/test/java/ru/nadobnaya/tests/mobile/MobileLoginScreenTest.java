package ru.nadobnaya.tests.mobile;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of different types of logging in on webinar mobile app")
@Tags({@Tag("mobile"), @Tag("positive")})
public class MobileLoginScreenTest extends TestBase {

    @Test
    @DisplayName("Logging in with correct user credentials")
    @Story("Testing of logging in with correct user credentials of existing user")
    @Severity(SeverityLevel.BLOCKER)
    void successfulLogInTest() {
        step("Press \"I already have an account\" button", () ->
            iHaveAccountButton.click()
        );
        step("Set user email, password and log in", () -> {
            etEmail.sendKeys("jennyqaguru@gmail.com");
            etPassword.sendKeys("123qweASD");
            logInButton.click();
        });
        step("Verify that log in is successful", () -> {
            startNewMeetingButton.shouldHave(text("NEW MEETING"));
            eventList.shouldHave(text("There are no webinars scheduled for that day"));
        });
    }

    @Test
    @DisplayName("Testing of a button to log in with SSO")
    @Story("Testing of the possibility to use corporate email to log in using single sign-on technology")
    @Severity(SeverityLevel.CRITICAL)
    void logInWithSSOButtonTest() {
        step("Press \"I already have an account\" button", () ->
            iHaveAccountButton.click()
        );
        step("Select log in with SSO", () ->
            signInSSO.click()
        );
        step("Verify that screen changed to log in with SSO", () ->
            title.shouldHave(text("Log in with SSO"))
        );
    }

    @Test
    @DisplayName("Testing of a button to recovery the password")
    @Story("Testing of the possibility to recover forgotten password without the need to repeat the email setting")
    @Severity(SeverityLevel.CRITICAL)
    void forgotPasswordButtonTest() {
        step("Press \"I already have an account\" button", () ->
            iHaveAccountButton.click()
        );
        step("Set user email", () ->
            etEmail.sendKeys(testData.testEmail)
        );
        step("Press \"Forgot the password\" button", () ->
            forgotPasswordButton.click()
        );
        step("Verify that user email is already set and the same", () ->
            etEmail.shouldHave(text(testData.testEmail))
        );
    }
}
