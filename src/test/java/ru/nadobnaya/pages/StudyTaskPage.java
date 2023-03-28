package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class StudyTaskPage {

    public StudyTaskPage verifyStudyTaskPageOpened() {
        $("body").shouldHave(text("Обучайте студентов и школьников онлайн"));
        return this;
    }

    public StudyTaskPage openPage() {
        open("/task/obuchenie_studentov/");
        return this;
    }

    public StudyTaskPage openDateAndTimeChoiceWindow() {
        $("body").$(byTagAndText("a", "Выбрать время")).click();
        switchTo().window(1);
        return this;
    }

}
