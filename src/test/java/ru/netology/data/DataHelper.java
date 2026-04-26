package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    private static final Faker FAKER_EN = new Faker(new Locale("en-US"));
    private static final Faker FAKER_RU = new Faker(new Locale("ru-RU"));
    private static final Random RANDOM = new Random();
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+-=[]{}|;:',.<>?/~`+";

    public static NumberCardApproved getNumberCardApproved() {
        return new NumberCardApproved("1111 2222 3333 4444");
    }

    public static NumberCardDeclined getNumberCardDeclined() {
        return new NumberCardDeclined("5555 6666 7777 8888");
    }

    //Генератор случайных значений на латинице
    public static String generateLatinInvalid(int length) {
        return FAKER_EN.regexify("[a-zA-Z]{" + length + "}");
    }

    //Генератор случайных значений на кириллице
    public static String generateCyrillicInvalid(int length) {
        return FAKER_RU.regexify("[а-яА-Я]{" + length + "}");
    }

    //Генератор случайных спецсимволов
    public static String generateSpecialChars(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(SPECIAL_CHARS.charAt(RANDOM.nextInt(SPECIAL_CHARS.length())));
    }
        return sb.toString();
    }
    public static String generateDigits(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }

    public static String generateDate(int years, String pattern) {
        return LocalDate.now().plusYears(years).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static int generateMonth(Faker faker) {
        return faker.number().numberBetween(1, 12);
    }

    public static String generateCodeCVC(Faker faker) {
        int cvcNumber = faker.number().numberBetween(0, 999);
        return String.format("%03d", cvcNumber);
    }

    @Value
    public static class NumberCardApproved {
        String numberCard;
    }

    @Value
    public static class NumberCardDeclined {
        String numberCard;
    }
    @Data
    @NoArgsConstructor
    public class PaymentEntity {
        private long id;
        private String status;
        private String payment_method;
        private String transaction_id;
    }
}

