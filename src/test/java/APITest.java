import api.FieldsApiDTO;
import api.MethodsApi;
import data.Info;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class APITest {
    @BeforeEach
    @DisplayName("Clear base table")
    void cleanTable() {
        SQL.clearTables();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/RequestDataApi.csv", numLinesToSkip = 1)
    void paymentApprovedCardTest(String number, String typeConnection, int statusCode, String status)  {
        FieldsApiDTO fieldsApiDTO = new FieldsApiDTO(number,
                Integer.parseInt(Info.getMonth()),
                Integer.parseInt(Info.getYear()),
                Info.getRandomOwner(),
                Integer.parseInt(Info.getRandomCvcCode()));

        ValidatableResponse response = MethodsApi.payRequest(fieldsApiDTO, typeConnection, statusCode);

        Assertions.assertEquals(SQL.getPaymentStatus(), status);
    }
}