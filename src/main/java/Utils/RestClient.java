package Utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    private static RequestSpecification baseRequest() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParams(Constants.getAuthParams())
                .log().all();
    }

    public static Response getRequest(String endpoint) {
        Logs.info("GET request sent to endpoint: " + endpoint);
        return baseRequest().get(Constants.BASE_URL + endpoint);
    }

    public static Response postRequest(Object body, String endpoint) {
        Logs.info("POST request sent to endpoint: " + endpoint);
        return baseRequest().body(body).post(Constants.BASE_URL + endpoint);
    }

    public static Response putRequest(Object body, String endpoint) {
        Logs.info("PUT request sent to endpoint: " + endpoint);
        return baseRequest().body(body).put(Constants.BASE_URL + endpoint);
    }

    public static Response patchRequest(Object body, String endpoint) {
        Logs.info("PATCH request sent to endpoint: " + endpoint);
        return baseRequest().body(body).patch(Constants.BASE_URL + endpoint);
    }

    public static Response deleteRequest(String endpoint) {
        Logs.info("DELETE request sent to endpoint: " + endpoint);
        return baseRequest().delete(Constants.BASE_URL + endpoint);
    }

    public static void validateStatusCode(Response response, int expectedCode) {
        if (response.statusCode() != expectedCode) {
            throw new RuntimeException(
                    String.format("Expected status %d but got %d. Response: %s",
                            expectedCode,
                            response.statusCode(),
                            response.body().asString())
            );
        }
    }

}