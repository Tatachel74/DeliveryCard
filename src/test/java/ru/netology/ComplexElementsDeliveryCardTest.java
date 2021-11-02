package ru.netology;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ComplexElementsDeliveryCardTest {
    @Test
    void shouldShowCityWithTwoLitters(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Че");
        $$(".menu-item").find(exactText("Челябинск")).click();
        $("[name='name']").setValue("Кислицина Татьяна");
        $("[name='phone']").setValue("+79227505555");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldCardDeliveryWithCalendar(){
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Че");
        $$(".menu-item").find(exactText("Челябинск")).click();
        $("[data-test-id=date] button").click();
        String nextWeekDay = LocalDate.now().plusWeeks(2).format(DateTimeFormatter.ofPattern("d"));
        String thisWeekDay = LocalDate.now().format(DateTimeFormatter.ofPattern("d"));
        if(Integer.parseInt(thisWeekDay) > Integer.parseInt(nextWeekDay)){
            $("div[data-step='1']").click();
        }
        $$(".calendar__day").find(exactText(nextWeekDay)).click();
        $("[name='name']").setValue("Кислицина Татьяна");
        $("[name='phone']").setValue("+79227505555");
        $("[class=checkbox__box]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }
}

