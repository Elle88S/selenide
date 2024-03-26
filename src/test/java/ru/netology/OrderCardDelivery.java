package ru.netology;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.remote.tracing.EventAttribute.setValue;


public class OrderCardDelivery {

        public String actualDate () {

            LocalDate date = LocalDate.now();
            date = date.plusDays(5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String text = date.format(formatter);
            LocalDate parsedDate = LocalDate.parse(text, formatter);

            return text;
        }


    @Test
    void deliveryCardForm(){


        open("http://localhost:9999/");
        SelenideElement form = $("[id=root]");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME), Keys.DELETE);
        form.$("[data-test-id=date] input").setValue(actualDate());
        form.$("[data-test-id=name] input").setValue("Иван Иванов");
        form.$("[name='phone']").setValue("+79999999999");
        form.$("[data-test-id=agreement]").click();
        form.$("button.button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldBe(Condition.text("Встреча успешно забронирована на "));



    }




}
