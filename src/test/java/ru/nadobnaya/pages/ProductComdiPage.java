package ru.nadobnaya.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ProductComdiPage {

    private final SelenideElement
            body = $("body");

    public ProductComdiPage openRequestPopup() {
        body.$(byText("Оставьте заявку")).click();
        return this;
    }

    public ProductComdiPage verifyRequestPopupOpen() {
        $(".t-popup.t-popup_show").shouldHave(text("Я хочу провести онлайн-мероприятие"));
        return this;
    }

    public ProductComdiPage closeRequestPopup() {
        $(".t-popup__close-icon").click();
        return this;
    }

    public ProductComdiPage openDetailsWindow() {
        body.$(byText("Подробнее")).click();
        switchTo().window(1);
        return this;
    }


}
