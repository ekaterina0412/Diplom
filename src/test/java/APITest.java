import API.FieldsApiDTO;
import API.MethodsApi;
import Data.Info;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.Matchers.equalTo;

public class APITest {
    @BeforeEach
    @DisplayName("Clear base table")
    void cleanTable() throws Exception {
        SQL.clearTables();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/RequestDataApi.csv", numLinesToSkip = 1)
    void paymentApprovedCardTest(String number, int typeConnection, int statusCode, String status) throws Exception {
        FieldsApiDTO fieldsApiDTO = new FieldsApiDTO(number,
                Integer.parseInt(Info.getRandomMonth()),
                Integer.parseInt(Info.getRandomYear()),
                Info.getRandomOwner(),
                Integer.parseInt(Info.getRandomCvcCode()));

        ValidatableResponse response = MethodsApi.payRequest(fieldsApiDTO, typeConnection);
        response.statusCode(statusCode);
        response.body("status", (ResponseAwareMatcher) response1 -> equalTo(status));
    }
}