package newApiStart;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class DeleteRequest {
    private static RequestSpecification requestSpec;

    @BeforeAll
    void setUp() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
    }

    @Test
    void deleteUser() {
        given().spec(requestSpec).when().delete("api/users/2").then().statusCode(204);
    }

}
