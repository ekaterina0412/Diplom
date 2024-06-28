package Data;

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

        public static String getInvalidMonth() {
            String[] months = {"1", "9", "33", "14"};
            Random random = new Random();
            int i = random.nextInt(months.length);
            return months[i];
        }

        public static String getPastYear() {
            String[] years = {"10", "11", "18"};
            Random random = new Random();
            int i = random.nextInt(years.length);
            return years[i];
        }

        public static String getInvalidCvcCode() {
            Random random = new Random();
            int rnd = random.nextInt(90) + 10;
            return Integer.toString(rnd);
        }

        public static String getUnknownFormatCardNumber() {
            return "4444 4444 4";
        }
    }
}
