package data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Info {
    private Info() {
    }

    public static String getMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getRandomOwner() {
        Faker faker = new Faker(new Locale("us"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getRandomCvcCode() {
        Random random = new Random();
        int rnd = random.nextInt(900) + 100;
        return Integer.toString(rnd);
    }

    public static class CardFields {
        private String month;
        private String year;
        private String owner;
        private String cvcCode;

        public CardFields(String month, String year, String owner, String cvcCode) {
            this.month = month;
            this.year = year;
            this.owner = owner;
            this.cvcCode = cvcCode;
        }
    }

    public static String getFirstCard() {
        String firstCardNumber;
        firstCardNumber = "4444444444444441";
        return firstCardNumber;
    }

    public static String getSecondCard() {
        String secondCardNumber;
        secondCardNumber = "4444444444444442";
        return secondCardNumber;
    }
}