package data;

import com.github.javafaker.Faker;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class Info {
    private Info() {
    }

    public static String getMonth() {
        String res = "";

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        String month_as_str = Integer.toString(month);
        if (month_as_str.length() == 2){
            res = month_as_str;
        }
        else {
            res = "0" + month_as_str;
        }

        return res;
    }

    public static String getYear() {
        String res = "";

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String year_as_str = Integer.toString(year);
        res = year_as_str.substring(year_as_str.length() - 2);

        return res;
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