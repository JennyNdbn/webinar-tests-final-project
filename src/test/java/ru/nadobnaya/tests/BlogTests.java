package ru.nadobnaya.tests;

import io.qameta.allure.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Owner("Evgeniia Nadobnaia")
public class BlogTests extends TestBase {

    @Test
    @DisplayName("Testing of blog page content")
    @Feature("Testing of blog page content")
    @Story("Testing of opening blog window from header and verifying blog header, article and footer")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("blog")
    void blogOpenTest() {
        step("Open main page", () -> {
            mainPage.openPage();
        });
        step("Open blog page from header", () -> {
            mainPage.openBlog();
        });
        step("Verify that blog has opened", () -> {
            blogPage.verifyBlogArticle();
        });
    }

    @Test
    @DisplayName("Testing of blog header")
    @Feature("Testing of blog page content")
    @Story("Testing of opening blog and verifying blog header")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("blog")
    void blogHeaderTest() {
        step("Open blog page", () -> {
            blogPage.openPage();
        });
        step("Verify blog header", () -> {
            blogPage.verifyBlogHeader();
        });
    }

    @Test
    @DisplayName("Testing of blog article")
    @Feature("Testing of blog page content")
    @Story("Testing of opening blog and verifying blog article")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("blog")
    void blogArticleTest() {
        step("Open blog page", () -> {
            blogPage.openPage();
        });
        step("Verify blog article", () -> {
            blogPage.verifyBlogArticle();
        });
    }

    @Test
    @DisplayName("Testing of blog footer")
    @Feature("Testing of blog page content")
    @Story("Testing of opening blog and verifying blog footer")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("blog")
    void blogFooterTest() {
        step("Open blog page", () -> {
            blogPage.openPage();
        });
        step("Verify blog footer", () -> {
            blogPage.verifyBlogFooter();
        });
    }

}
