package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.TitlePage;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {
    private final Faker faker = new Faker(new Locale("en"));

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeAll
    static void setUp() {
        SQLHelper.cleanDatabase();
    }

    @Test
    public void cardNumberLatinLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = DataHelper.generateLatinInvalid(16);
        creditPage.fillCardNumber(invalidCardNumber);
        // 5. Проверка пустого поля
        creditPage.cardNumberValue("");
    }

    @Test
    public void cardNumberCyrillicLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = DataHelper.generateCyrillicInvalid(16);
        creditPage.fillCardNumber(invalidCardNumber);
        // 5. Проверка пустого поля
        creditPage.cardNumberValue("");
    }

    @Test
    public void cardNumberSpecialCharacterShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = String.valueOf(DataHelper.generateSpecialChars(16));
        creditPage.fillCardNumber(invalidCardNumber);
        // 5. Проверка пустого поля
        creditPage.cardNumberValue("");
    }

    @Test
    public void cardNumber15CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = DataHelper.generateDigits(15);
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(invalidCardNumber, month, year, owner, cvc);
        creditPage.cardNumberErrorText("Неверный формат");
    }

    @Test
    public void cardNumber17CharacterShouldShow16() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные (генерируем 17 цифр)
        String seventeenDigits = DataHelper.generateDigits(17);
        // Ожидаем, что в поле останутся только первые 16 цифр
        String expectedValue = seventeenDigits.substring(0, 16);
        // Форматируем их с пробелами (каждые 4 цифры)
        String expectedValueWithSpaces = expectedValue.replaceAll("(.{4})", "$1 ").trim();
        // 4. Вводим 17 цифр
        creditPage.fillCardNumber(seventeenDigits);
        // 5. Проверяем значение поля.
        creditPage.cardNumberValue(expectedValueWithSpaces);
    }

    @Test
    public void cardNumber0CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = DataHelper.generateDigits(0);
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("ru");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(invalidCardNumber, month, year, owner, cvc);
        creditPage.cardNumberErrorText("Неверный формат");
    }

    @Test
    public void monthLatinLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String month = DataHelper.generateLatinInvalid(2);
        creditPage.fillMonth(month);
        // 4. Проверка пустого поля
        creditPage.monthValue("");
    }

    @Test
    public void monthCyrillicLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String month = DataHelper.generateCyrillicInvalid(2);
        creditPage.fillMonth(month);
        // 4. Проверка пустого поля
        creditPage.monthValue("");
    }

    @Test
    public void monthSpecialCharacterShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String month = DataHelper.generateSpecialChars(2);
        creditPage.fillMonth(month);
        // 4. Проверка пустого поля
        creditPage.monthValue("");
    }

    @Test
    public void month1CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        String month = DataHelper.generateDigits(1);
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("ru");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.monthErrorText("Неверный формат");
    }

    @Test
    public void month0CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        String month = DataHelper.generateDigits(0);
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("ru");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.monthErrorText("Неверный формат");
    }

    @Test
    public void month3CharacterShouldShow2() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные (генерируем 3 цифры)
        String month = DataHelper.generateDigits(3);
        // Ожидаем, что в поле останутся только первые 2 цифры
        String expectedValue = month.substring(0, 2);
        // 4. Вводим 3 цифры
        creditPage.fillMonth(month);
        // 5. Проверяем значение поля.
        creditPage.monthValue(expectedValue);
    }

    @Test
    public void yearLatinLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String year = DataHelper.generateLatinInvalid(2);
        creditPage.fillYear(year);
        // 4. Проверка пустого поля
        creditPage.yearValue("");
    }

    @Test
    public void yearCyrillicLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String year = DataHelper.generateCyrillicInvalid(2);
        creditPage.fillYear(year);
        // 4. Проверка пустого поля
        creditPage.yearValue("");
    }

    @Test
    public void yearSpecialCharacterShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String year = DataHelper.generateSpecialChars(2);
        creditPage.fillYear(year);
        // 4. Проверка пустого поля
        creditPage.yearValue("");
    }

    @Test
    public void year1CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        String year = DataHelper.generateDigits(1);
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.yearErrorText("Неверный формат");
    }

    @Test
    public void year0CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        String year = DataHelper.generateDigits(0);
        creditPage.fillYear(year);
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.yearErrorText("Неверный формат");
    }

    @Test
    public void year3CharactersShouldShow2() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String threeDigits = DataHelper.generateDigits(3);
        // 4. Ожидаем, что в поле останутся только первые 2 цифры
        String expectedValue = threeDigits.substring(0, 2);
        // 5. Вводим 3 цифры
        creditPage.fillYear(threeDigits);
        // 5. Проверяем значение поля.
        creditPage.yearValue(expectedValue);
    }

    @Test
    public void ownerLatinLettersShouldShowSuccess() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // 4. Проверка результата
        creditPage.waitSuccessNotification();
    }

    @Test
    public void ownerCyrillicLettersShouldShowSuccess() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = DataHelper.generateName("ru");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // 4. Проверка результата
        creditPage.waitSuccessNotification();
    }

    @Test
    public void ownerCyrillicLettersWithYoShouldShowSuccess() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = "Алёна";
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // 4. Проверка результата
        creditPage.waitSuccessNotification();
    }

    @Test
    public void ownerCyrillicLettersWithIShouldShowSuccess() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = "Йозеф";
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // 4. Проверка результата
        creditPage.waitSuccessNotification();
    }

    @Test
    public void ownerCyrillicLettersWithDashShouldShowSuccess() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = "Anna-Maria";
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // 4. Проверка результата
        creditPage.waitSuccessNotification();
    }

    @Test
    public void ownerSpecialCharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = DataHelper.generateSpecialChars(10);
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.ownerErrorText("Неверный формат");
    }

    @Test
    public void ownerNumbersShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = DataHelper.generateDigits(10);
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.ownerErrorText("Неверный формат");
    }

    @Test
    public void owner0CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = DataHelper.generateLatinInvalid(0);
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.ownerErrorText("Поле обязательно для заполнения");
    }

    @Test
    public void owner1CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner = DataHelper.generateLatinInvalid(1);
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.waitSuccessNotification();
    }

    @Test
    public void cvcLatinLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateLatinInvalid(2);
        creditPage.fillCvc(cvc);
        // 4. Проверка пустого поля
        creditPage.cvcValue("");
    }

    @Test
    public void cvcCyrillicLettersShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateCyrillicInvalid(2);
        creditPage.fillCvc(cvc);
        // 4. Проверка пустого поля
        creditPage.cvcValue("");
    }

    @Test
    public void cvcSpecialCharacterShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateSpecialChars(2);
        creditPage.fillCvc(cvc);
        // 4. Проверка пустого поля
        creditPage.cvcValue("");
    }

    @Test
    public void cvc0CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("ru");
        String cvc = DataHelper.generateDigits(0);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.cvcErrorText("Неверный формат");
        ;
    }

    @Test
    public void cvc1CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("ru");
        String cvc = DataHelper.generateDigits(1);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.cvcErrorText("Неверный формат");
    }

    @Test
    public void cvc2CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("ru");
        String cvc = DataHelper.generateDigits(2);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.cvcErrorText("Неверный формат");
    }

    @Test
    public void cvc4CharacterShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String forDigits = DataHelper.generateDigits(4);
        // 4. Ожидаем, что в поле останутся только первые 3 цифры
        String expectedValue = forDigits.substring(0, 3);
        // 5. Вводим 4 цифры
        creditPage.fillCvc(forDigits);
        // 5. Проверяем значение поля.
        creditPage.cvcValue(expectedValue);
    }

    @Test
    public void fillingInAllFieldsWithValidData() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // 2. Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Подготовка данных через DataHelper
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);

        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // Проверка результата
        creditPage.waitSuccessNotification();
    }

    @Test
    public void submittingFormWithEmptyFields() {
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // Переходим на страницу кредита
        var creditPage = new CreditPage();

        // Нажать на кнопку "Продолжить"
        creditPage.clickContinue();

        // 4. Проверить ошибки под всеми полями
        creditPage.cardNumberErrorText("Неверный формат");
        creditPage.monthErrorText("Неверный формат");
        creditPage.yearErrorText("Неверный формат");
        creditPage.ownerErrorText("Поле обязательно для заполнения");
        creditPage.cvcErrorText("Неверный формат");
    }

    @Test
    public void submittingFormWithDeclinedCardNumber() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // 2. Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Подготовка данных через DataHelper
        var cardInfo = DataHelper.getNumberCardDeclined();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);

        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // Проверка результата
        creditPage.waitErrorNotification();
    }

    @Test
    public void submittingFormWithInvalidCardNumber() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // 2. Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Подготовка данных через DataHelper
        var cardInfo = DataHelper.generateDigits(16);
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);

        creditPage.fillForm(cardInfo, month, year, owner, cvc);

        // Проверка результата
        creditPage.waitErrorNotification();
    }

    @Test
    public void submittingFormWithInvalidMonth() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // 2. Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Подготовка данных через DataHelper
        var cardInfo = DataHelper.getNumberCardApproved();
        String month = "15";
        var year = DataHelper.generateDate(1, "yy");
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);

        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // Проверка результата
        creditPage.monthErrorText("Неверно указан срок действия карты");
    }

    @Test
    public void submittingFormWithInvalidYear() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // 2. Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Подготовка данных через DataHelper
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        String year = "24";
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);

        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // Проверка результата
        creditPage.yearErrorText("Истёк срок действия карты");
    }
}

