package tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import utility.Envs;

public class TestConfigurator {

    @BeforeAll
    public static void initTests() {
        Envs.init();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
