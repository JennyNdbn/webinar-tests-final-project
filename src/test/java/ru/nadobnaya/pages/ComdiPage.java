package ru.nadobnaya.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ComdiPage {

    public ComdiPage verifyDetailsWindowOpen() {
        $(".promo-content").shouldHave(text("Оставайтесь на связи с сотрудниками и клиентами"));
        return this;
    }

}
