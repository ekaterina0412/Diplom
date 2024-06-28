package API;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class MethodsApi {

    public static final int payment = 0;
    public static final int credit = 1;

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private MethodsApi() {
    }

    public static ValidatableResponse payRequest(FieldsApiDTO apiDTO, int typeConnection) throws Exception {
        String url;
        if (typeConnection == payment) {
            url = "/payment";

        } else if (typeConnection == credit) {
            url = "/credit";
        }
        else
        {
            throw new Exception();
        }
        return given()
                .spec(requestSpec)
                .body(apiDTO)
                .when()
                .post(url)
                .then();
    }
}
