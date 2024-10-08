package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class MethodsApi {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private MethodsApi() {
    }

    public static ValidatableResponse payRequest(FieldsApiDTO apiDTO, String url, int statusCode) {
        return given()
                .spec(requestSpec)
                .body(apiDTO)
                .when()
                .post(url)
                .then()
                .statusCode(statusCode);
    }
}
