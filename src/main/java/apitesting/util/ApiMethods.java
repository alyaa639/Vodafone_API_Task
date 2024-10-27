package apitesting.util;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiMethods {
    public static Response post(String endpoint, String body) {
        return RestAssured
                .given()
                .baseUri(Config.getBaseUri())
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response get(String baseURI,String endpoint) {
        return RestAssured
                .given()
                .baseUri(baseURI)
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
    public static Response delete(String baseURI,String endpoint ) {
        return RestAssured
                .given()
                .baseUri(baseURI)
                .delete(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}




