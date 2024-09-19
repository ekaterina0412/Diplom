package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class InvalidDataInfo {

    private InvalidDataInfo() {
    }

    public static class CardFields {
        private String invalidMonth;
        private String invalidYear;
        private String invalidOwner;
        private String invalidCvcCode;

        public CardFields(String invalidMonth, String invalidYear, String invalidOwner, String invalidCvcCode) {
            this.invalidMonth = invalidMonth;
            this.invalidYear = invalidYear;
            this.invalidOwner = invalidOwner;
            this.invalidCvcCode = invalidCvcCode;
        }

        public static String getInvalidMonth1() {
            String[] months = {"1", "9"};
            Random random = new Random();
            int i = random.nextInt(months.length);
            return months[i];
        }

        public static String getInvalidMonth2() {
            String[] months = {"33", "14"};
            Random random = new Random();
            int i = random.nextInt(months.length);
            return months[i];
        }

        public static String getPastYear() {
            return LocalDate.now().plusYears(-1).format(DateTimeFormatter.ofPattern("yy"));
            /*String res = "";

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR) - 1;
            String year_as_str = Integer.toString(year);
            res = year_as_str.substring(year_as_str.length() - 2);

            return res;*/
        }

        public static String getVeryOldYear() {
            return LocalDate.now().plusYears(-10).format(DateTimeFormatter.ofPattern("yy"));
            /*String res = "";

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR) - 10;
            String year_as_str = Integer.toString(year);
            res = year_as_str.substring(year_as_str.length() - 2);

            return res;*/
        }

        public static String getInvalidCvcCode() {
            Random random = new Random();
            int rnd = random.nextInt(90) + 10;
            return Integer.toString(rnd);
        }

        public static String getInvalidRusOwner() {
            return "Тест Тест Тест";
        }

        public static String getUnknownFormatCardNumber() {
            return "4444 4444 4";
        }
    }
}
