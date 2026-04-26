package ru.netology.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
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

    @Test
    public void TA001_CardNumber_LatinLetters_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = DataHelper.generateLatinInvalid(16);
        String actualValue = creditPage.getCardNumberValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA002_CardNumber_CyrillicLetters_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = DataHelper.generateCyrillicInvalid(16);
        String actualValue = creditPage.getCardNumberValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA003_CardNumber_SpecialCharacter_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String invalidCardNumber = String.valueOf(DataHelper.generateSpecialChars(16));
        String actualValue = creditPage.getCardNumberValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA004_CardNumber_15Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getCardNumberErrorText());
    }
    @Test
    public void TA005_CardNumber_17Character_ShouldShow16() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные (генерируем 17 цифр)
        String seventeenDigits = DataHelper.generateDigits(17);
        creditPage.fillCardNumber(seventeenDigits);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getCardNumberValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (16)
        assertEquals(16, actualLength);
    }
    @Test
    public void TA006_CardNumber_0Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getCardNumberErrorText());
    }

    @Test
    public void TA007_Month_LatinLetters_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String month = DataHelper.generateLatinInvalid(2);
        creditPage.fillMonth(month);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getMonthValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (16)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA008_Month_CyrillicLetters_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String month = DataHelper.generateCyrillicInvalid(2);
        creditPage.fillMonth(month);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getMonthValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (16)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA009_Month_SpecialCharacter_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String month = DataHelper.generateSpecialChars(2);
        creditPage.fillMonth(month);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getMonthValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (16)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA0010_Month_1Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getMonthErrorText());
    }
    @Test
    public void TA0011_Month_0Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getMonthErrorText());
    }
    @Test
    public void TA0012_Month_3Character_ShouldShow2() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные (генерируем 3 цифры)
        String threeDigits = DataHelper.generateDigits(3);
        creditPage.fillMonth(threeDigits);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getMonthValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (2)
        assertEquals(2, actualLength);
    }
    @Test
    public void TA0013_Year_LatinLetters_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String year = DataHelper.generateLatinInvalid(2);
        creditPage.fillYear(year);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getYearValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA0014_Year_CyrillicLetters_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String year = DataHelper.generateCyrillicInvalid(2);
        creditPage.fillYear(year);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getYearValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA0015_Year_SpecialCharacter_ShouldShowEmptyField() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String year = DataHelper.generateSpecialChars(2);
        creditPage.fillYear(year);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getYearValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA0016_Year_1Character_ShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        String year= DataHelper.generateDigits(1);
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getYearErrorText());
    }
    @Test
    public void TA0017_Year_0Character_ShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        String year= DataHelper.generateDigits(0);
        creditPage.fillYear(year);
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getYearErrorText());
    }
    @Test
    public void TA0018_Year_3Characters_ShouldShow2() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String threeDigits = DataHelper.generateDigits(3);
        creditPage.fillYear(threeDigits);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getYearValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (2)
        assertEquals(2, actualLength);
    }
    @Test
    public void TA019_Owner_LatinLetters_ShouldShowSuccess(){
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
    public void TA020_Owner_CyrillicLetters_ShouldShowSuccess(){
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
    public void TA021_Owner_CyrillicLettersWithYo_ShouldShowSuccess(){
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
    public void TA022_Owner_CyrillicLettersWithI_ShouldShowSuccess(){
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
    public void TA023_Owner_CyrillicLettersWithDash_ShouldShowSuccess(){
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
    public void TA0024_Owner_SpecialCharacter_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getOwnerErrorText());
    }
    @Test
    public void TA0025_Owner_Numbers_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getOwnerErrorText());
    }
    @Test
    public void TA0026_Owner_0Character_ShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner= DataHelper.generateLatinInvalid(0);
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.waitInputSubErrorVisible();
        assertEquals("Поле обязательно для заполнения", creditPage.getOwnerErrorText());
    }
    @Test
    public void TA0027_Owner_1Character_ShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        var year = DataHelper.generateDate(1, "yy");
        String owner= DataHelper.generateLatinInvalid(1);
        var cvc = DataHelper.generateCodeCVC(faker);
        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);
        creditPage.waitSuccessNotification();
    }
    @Test
    public void TA028_CVC_LatinLetters_ShouldShowEmptyField(){
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateLatinInvalid(3);
        creditPage.fillCvc(cvc);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getCVCValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA029_CVC_CyrillicLetters_ShouldShowEmptyField(){
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateCyrillicInvalid(3);
        creditPage.fillCvc(cvc);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getCVCValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA030_CVC_SpecialCharacter_ShouldShowEmptyField(){
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateSpecialChars(3);
        creditPage.fillCvc(cvc);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getCVCValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(0, actualLength);
    }
    @Test
    public void TA0031_CVC_0Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getCvcErrorText());
    }
    @Test
    public void TA0032_CVC_1Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getCvcErrorText());
    }
    @Test
    public void TA0033_CVC_2Character_ShouldShowError() {
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
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getCvcErrorText());
    }
    @Test
    public void TA0034_CVC_4Character_ShouldShowError() {
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();
        // 2. Инициализировать страницу кредита
        var creditPage = new CreditPage();
        // 3. Тестовые данные
        String cvc = DataHelper.generateDigits(4);
        creditPage.fillCvc(cvc);
        // 4. Получаем значение, удаляем пробелы и считаем длину
        String actualValue = creditPage.getCVCValue();
        int actualLength = actualValue.replace(" ", "").length();
        // 5. Сравниваем длину с ожидаемой (0)
        assertEquals(3, actualLength);
    }

    @Test
    public void TA035_FillingInAllFields_WithValidData(){
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
    public void TA036_SubmittingForm_WithEmptyFields() {
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit(); // Переходим на страницу кредита

        var creditPage = new CreditPage();

        // Нажать на кнопку "Продолжить"
        creditPage.clickContinue();

        // 4. Проверить ошибки под всеми полями
        creditPage.waitInputSubErrorVisible();
        assertEquals("Неверный формат", creditPage.getCardNumberErrorText());
        assertEquals("Неверный формат", creditPage.getMonthErrorText());
        assertEquals("Неверный формат", creditPage.getYearErrorText());
        assertEquals("Поле обязательно для заполнения", creditPage.getOwnerErrorText());
        assertEquals("Неверный формат", creditPage.getCvcErrorText());
    }
    @Test
    public void TA037_SubmittingForm_WithDeclinedCardNumber(){
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
    public void TA038_SubmittingForm_WithInvalidCardNumber(){
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
    public void TA039_SubmittingForm_WithInvalidMonth(){
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
        assertEquals("Неверно указан срок действия карты", creditPage.getMonthErrorText());
    }
    @Test
    public void TA040_SubmittingForm_WithInvalidYear(){
        // 1. Перейти на страницу покупки в кредит
        var titlePage = new TitlePage();
        titlePage.TransitionToCredit();

        // 2. Переходим на страницу кредита
        var creditPage = new CreditPage();

        // 3. Подготовка данных через DataHelper
        var cardInfo = DataHelper.getNumberCardApproved();
        var month = String.format("%02d", DataHelper.generateMonth(faker));
        String year= "24";
        var owner = DataHelper.generateName("en");
        var cvc = DataHelper.generateCodeCVC(faker);

        creditPage.fillForm(cardInfo.getNumberCard(), month, year, owner, cvc);

        // Проверка результата
        assertEquals("Истёк срок действия карты", creditPage.getYearErrorText());
    }
    }

