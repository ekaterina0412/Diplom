import java.io.File;

public class ReaderProperties {

    public static final int mySQL = 0;
    public static final int postgres = 1;

    private ReaderProperties() {}

    public static void getDatasourceProperties(int typeDatasourse) throws Exception {
        File file = new File("./application.properties");

        String datasource = System.getProperty("db.url");
        if (typeDatasourse == mySQL) {
            datasource = "jdbc:mysql://localhost:3306/app";
        } else if (typeDatasourse == postgres) {
            datasource = "jdbc:postgresql://localhost:5432/app";
        } else {
            throw new Exception();
        }
    }
}