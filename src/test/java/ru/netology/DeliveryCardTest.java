package ru.netology;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DeliveryCardTest {
    @Test
    void shouldCardDeliveryWithAllData() {
        String dateToEnter = LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Челябинск");
        $("[data-test-id=date] input.input__control").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input.input__control").setValue(dateToEnter);
        $("[name='name']").setValue("Кислицина Татьяна");
        $("[name='phone']").setValue("+79227505555");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldCardDeliveryWithoutCity(){
        open("http://localhost:9999");
        $("[name='name']").setValue("Кислицина Татьяна");
        $("[name='phone']").setValue("+79227505555");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryWithoutName(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Челябинск");
        $("[name='phone']").setValue("+79227505555");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryWithEngName(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Челябинск");
        $("[name='name']").setValue("Kislitcina Tatiana");
        $("[name='phone']").setValue("+79227505555");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldCardDeliveryWithoutPhone(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Челябинск");
        $("[name='name']").setValue("Кислицина Татьяна");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryWithoutCheckbox(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Челябинск");
        $("[name='name']").setValue("Кислицина Татьяна");
        $("[name='phone']").setValue("+79227505555");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));
    }
}

