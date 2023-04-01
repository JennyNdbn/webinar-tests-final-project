package ru.nadobnaya.tests.web;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
@Feature("Testing of education of university students page content")
public class StudentsEducationTests extends TestBase {

    @Test
    @DisplayName("Testing of opening education of university students page")
    @Story("Testing of opening education of university students page from header by using hover action")
    @Severity(SeverityLevel.BLOCKER)
    @Tags({@Tag("UI"), @Tag("university_students")})
    void openEducationPageTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open education of university students page from tasks popup", () -> {
            mainPage.headerHover(testData.tasks)
                    .openStudentStudyTask();
        });
        step("Verify that education of university students page has opened", () -> {
            studyTaskPage.verifyStudyTaskPageOpened();
        });
    }

    @Test
    @DisplayName("Testing of opening enroll page")
    @Story("Testing of opening enroll page from education page")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("UI"), @Tag("university_students")})
    void tasksEducationTest() {
        step("Open education of university students page", () -> {
            studyTaskPage.openPage();
        });
        step("Open enroll page", () -> {
            studyTaskPage.openDateAndTimeChoiceWindow();
        });
        step("Verify that enroll page has opened", () -> {
            studentEnrollPage.verifyDateAndTimeChoiceWindowOpen();
        });
    }

}
