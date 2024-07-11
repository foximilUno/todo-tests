package utility;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

public class Rest {

    static {
        RestAssured.defaultParser = Parser.JSON;
    }

    public static class Post {
        public static <T> void sendPostWithEmptyResponse(String endpoint, T body, int expectedHttpStatusCode) {
            RestAssured.given()
                .body(body)
                .contentType(ContentType.JSON)
                .post(endpoint)
                .then()
                .statusCode(expectedHttpStatusCode);
        }
    }

    public static class Get {
        public static <T> T sendGet(String endpoint, Map<String, String> queries, Class<T> classCast, int expectedHttpStatusCode) {
            return RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParams(queries)
                .get(endpoint)
                .then()
                .statusCode(expectedHttpStatusCode)
                .extract()
                .as(classCast);
        }

        public static <T> T[] sendGet(String endpoint, Map<String, String> queries, int expectedHttpStatusCode, Class<T[]> classCast) {
            return RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParams(queries)
                .get(endpoint)
                .then()
                .statusCode(expectedHttpStatusCode)
                .extract()
                .as(classCast);
        }

        public static void sendGetWithError(String endpoint, Map<String, String> queries, int expectedHttpStatusCode) {
            RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParams(queries)
                .get(endpoint)
                .then()
                .statusCode(expectedHttpStatusCode);
        }
    }

    public static class Put {

        public static <T> void sendPut(String endpoint, T body, int expectedHttpStatusCode) {
            RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .put(endpoint)
                .then()
                .statusCode(expectedHttpStatusCode);
        }
    }

    public static class Delete {
        public static <T> void sendDelete(String endpoint, Map<String, String> headers, int expectedHttpStatusCode) {
            RestAssured.given()
                .contentType(ContentType.JSON)
                .headers(headers)
                .delete(endpoint)
                .then()
                .statusCode(expectedHttpStatusCode);
        }
    }
}
