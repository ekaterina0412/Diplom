import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class SQL {
    public static String getPaymentStatus()  {
        File file = new File("./application.properties");
        Properties properties = new Properties();

        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String selectStatus = "SELECT status FROM payment_entity";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(System.getProperty("db.url"),
                    properties.getProperty("spring.datasource.username"),
                    properties.getProperty("spring.datasource.password"));

            PreparedStatement cardStatusRequest = conn.prepareStatement(selectStatus);

            ResultSet cardStatus = cardStatusRequest.executeQuery();
            cardStatus.next();
            return cardStatus.getString("status");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static String getCreditStatus() {
        File file = new File("./application.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //String[] parts = properties.getProperty("spring.datasource.url").split(":");
        //parts[2] = "//localhost";
        //String url = String.join(":", parts);

        String selectStatus = "SELECT status FROM credit_request_entity";

        //Connection conn = DriverManager.getConnection(url,
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(System.getProperty("db.url"),
                    properties.getProperty("spring.datasource.username"),
                    properties.getProperty("spring.datasource.password"));

            PreparedStatement cardStatusRequest = conn.prepareStatement(selectStatus);

            ResultSet cardStatus = cardStatusRequest.executeQuery();
            cardStatus.next();
            return cardStatus.getString("status");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearTables() {
        File file = new File("./application.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //String[] parts = properties.getProperty("spring.datasource.url").split(":");
        //parts[2] = "//localhost";
        //String url = String.join(":", parts);

        String truncateCreditRequestEntity = "TRUNCATE TABLE credit_request_entity";
        String truncateOrderEntity = "TRUNCATE TABLE order_entity";
        String truncatePaymentEntity = "TRUNCATE TABLE payment_entity";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(System.getProperty("db.url"),
                    properties.getProperty("spring.datasource.username"),
                    properties.getProperty("spring.datasource.password"));

            PreparedStatement clearCreditRequestEntity = conn.prepareStatement(truncateCreditRequestEntity);
            clearCreditRequestEntity.execute();

            PreparedStatement clearOrderEntity = conn.prepareCall(truncateOrderEntity);
            clearOrderEntity.execute();

            PreparedStatement clearPaymentEntity = conn.prepareStatement(truncatePaymentEntity);
            clearPaymentEntity.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}