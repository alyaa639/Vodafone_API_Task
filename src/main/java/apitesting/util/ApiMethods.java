package apitesting.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiMethods {
    public static Response post(String endpoint, String body) {
        // Create a map to store headers
        return RestAssured
                .given()
                .baseUri(Config.getBaseUri())
                .header("Content-Type", "application/json")
                .body(body)
                .post(endpoint)
                .then()
                .log().all() // Log all response details
                .extract()
                .response();
    }

    public static Response get(String endpoint) {
        return RestAssured
                .given()
                .baseUri(Config.getBaseUri())
                .get(Config.getEndpoint())
                .then()
                .log().all() // Log all response details
                .extract()
                .response();
    }


    public static Response getWithQueryParams(String endpointWithQueryparam) {
        return RestAssured
                .given()
                .baseUri(Config.getBaseUri())
                .get(Config.getqueyparamEndpoint())
                .then()
                .log().all() // Log all response details
                .extract()
                .response();
    }
}


