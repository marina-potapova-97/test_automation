package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditPage {
    SelenideElement cardNumberInput = $("[placeholder='0000 0000 0000 0000']");
    SelenideElement monthInput = $("[placeholder='08']");
    SelenideElement yearInput = $("[placeholder='22']");
    SelenideElement ownerInput = $x("//span[text()='Владелец']/..//input");
    SelenideElement cvcInput = $("[placeholder='999']");
    SelenideElement continueButton = $(byText("Продолжить"));

    SelenideElement successNotification = $(".notification_status_ok");
    SelenideElement errorNotification = $(".notification_status_error");
    SelenideElement inputSubTextError = $(".input__sub");

    // ЛОКАТОРЫ ОШИБОК ПОД КОНКРЕТНЫМИ ПОЛЯМИ

    SelenideElement cardNumberError = $x("//span[text()='Номер карты']/..//span[@class='input__sub']");
    SelenideElement monthError = $x("//span[text()='Месяц']/..//span[@class='input__sub']");
    SelenideElement yearError = $x("//span[text()='Год']/..//span[@class='input__sub']");
    SelenideElement ownerError = $x("//span[text()='Владелец']/..//span[@class='input__sub']");
    SelenideElement cvcError = $x("//span[text()='CVC/CVV']/..//span[@class='input__sub']");

    public void fillForm(String card, String month, String year, String owner, String cvc) {
        cardNumberInput.setValue(card);
        monthInput.setValue(month);
        yearInput.setValue(year);
        ownerInput.setValue(owner);
        cvcInput.setValue(cvc);
        continueButton.click();
    }

    public void waitSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Операция одобрена банком"));
    }

    public void waitErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void waitInputSubErrorVisible() {
        inputSubTextError.shouldBe(visible);
    }

    public void fillCardNumber(String cardNumber) {
        cardNumberInput.clear();
        cardNumberInput.setValue(cardNumber);
    }

    // Получить значение поля номера карты
    public void cardNumberValue(String expectedValue) {
        cardNumberInput.shouldHave(Condition.value(expectedValue));
    }

    public void fillMonth(String cardNumber) {
        monthInput.clear();
        monthInput.setValue(cardNumber);
    }

    // Получить значение поля Месяц
    public void monthValue(String expectedValue) {
        monthInput.shouldHave(Condition.value(expectedValue));
    }

    public void fillYear(String cardNumber) {
        yearInput.clear();
        yearInput.setValue(cardNumber);
    }

    // Получить значение поля Год
    public void yearValue(String expectedValue) {
        yearInput.shouldHave(Condition.value(expectedValue));
    }

    public void fillCvc(String cardNumber) {
        cvcInput.clear();
        cvcInput.setValue(cardNumber);
    }

    // Получить значение поля Год
    public void cvcValue(String expectedValue) {
        cvcInput.shouldHave(Condition.value(expectedValue));
    }

    public void clickContinue() {
        continueButton.click();
    }

    // МЕТОДЫ ДЛЯ ПОЛУЧЕНИЯ ТЕКСТА ОШИБОК
    public void cardNumberErrorText(String expectedText) {
        cardNumberError.shouldBe(visible).shouldHave(Condition.text(expectedText));
    }

    public void monthErrorText(String expectedText) {
        monthError.shouldBe(visible).shouldHave(Condition.text(expectedText));
    }

    public void yearErrorText(String expectedText) {
        yearError.shouldBe(visible).shouldHave(Condition.text(expectedText));
    }

    public void ownerErrorText(String expectedText) {
        ownerError.shouldBe(visible).shouldHave(Condition.text(expectedText));
    }

    public void cvcErrorText(String expectedText) {
        cvcError.shouldBe(visible).shouldHave(Condition.text(expectedText));
    }


}
