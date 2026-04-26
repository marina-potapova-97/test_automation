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
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }
    public void waitErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void waitInputSubErrorVisible() {
        inputSubTextError.shouldBe(visible);
    }
    public void fillCardNumber(String cardNumber) {
        cardNumberInput.clear();
        cardNumberInput.setValue(cardNumber);
    }
    // Получить значение поля номера карты
    public String getCardNumberValue() {
        return cardNumberInput.getValue();
    }

    // Проверить, что поле не принимает больше maxLength символов
    public void assertCardNumberMaxLength(int maxLength) {
        String value = cardNumberInput.getValue().replace(" ", "");
        assertTrue(value.length() <= maxLength);
    }
    public void fillMonth(String month) {
        monthInput.clear();
        monthInput.setValue(month);
    }
    // Получить значение поля Месяц
    public String getMonthValue() {
        return monthInput.getValue();
    }
    public void assertMonthMaxLength(int maxLength) {
        String value = monthInput.getValue().replace(" ", "");
        assertTrue(value.length() <= maxLength);
    }
    public void fillYear(String year) {
        yearInput.clear();
        yearInput.setValue(year);
    }
    // Получить значение поля Год
    public String getYearValue() {
        return yearInput.getValue();
    }
    public void assertYearMaxLength(int maxLength) {
        String value = yearInput.getValue().replace(" ", "");
        assertTrue(value.length() <= maxLength);
    }
    public void fillCvc(String cvc) {
        cvcInput.clear();
        cvcInput.setValue(cvc);
    }
    // Получить значение поля Год
    public String getCVCValue() {
        return cvcInput.getValue();
    }
    public void assertCVCMaxLength(int maxLength) {
        String value = cvcInput.getValue().replace(" ", "");
        assertTrue(value.length() <= maxLength);
    }
    public void clickContinue() {
        continueButton.click();
    }
    // МЕТОДЫ ДЛЯ ПОЛУЧЕНИЯ ТЕКСТА ОШИБОК
    public String getCardNumberErrorText() {
        return cardNumberError.shouldBe(visible).getText();
    }
    public String getMonthErrorText() {
        return monthError.shouldBe(visible).getText();
    }
    public String getYearErrorText() {
        return yearError.shouldBe(visible).getText();
    }
    public String getOwnerErrorText() {
        return ownerError.shouldBe(visible).getText();
    }
    public String getCvcErrorText()
    { return cvcError.shouldBe(visible).getText();
    }


}
