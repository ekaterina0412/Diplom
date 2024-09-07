import api.FieldsApiDTO;
import api.MethodsApi;
import data.Info;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.hamcrest.core.IsEqual.equalTo;

public class APITest {
    @BeforeEach
    @DisplayName("Clear base table")
    void cleanTable() {
        SQL.clearTables();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/RequestDataApi.csv", numLinesToSkip = 1)
    void paymentApprovedCardTest(String number, int typeConnection, int statusCode, String status)  {
        FieldsApiDTO fieldsApiDTO = new FieldsApiDTO(number,
                Integer.parseInt(Info.getMonth()),
                Integer.parseInt(Info.getYear()),
                Info.getRandomOwner(),
                Integer.parseInt(Info.getRandomCvcCode()));

        String url = "/payment";
        if (typeConnection == 1) {
            url = "/credit";
        }

        ValidatableResponse response = MethodsApi.payRequest(fieldsApiDTO, url);

        response.statusCode(statusCode);
        response.body("status", (ResponseAwareMatcher) response1 -> equalTo(status));
    }
}