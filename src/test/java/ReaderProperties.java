import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class ReaderProperties {

    public static final int mySQL = 0;
    public static final int postgres = 1;

    private ReaderProperties() {}

    public static void getDatasourceProperties(int typeDatasourse) throws Exception {
        File file = new File("./application.properties");
        Properties properties = new Properties();
        properties.load(new FileReader(file));

        String datasource = properties.getProperty("spring.datasource.url");
        if (typeDatasourse == mySQL) {
            datasource = "jdbc:mysql://localhost:3306/app";
        } else if (typeDatasourse == postgres) {
            datasource = "jdbc:postgresql://localhost:5432/app";
        } else {
            throw new Exception();
        }
    }
}