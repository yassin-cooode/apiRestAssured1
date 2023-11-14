package newApiStart;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class GetRequests {
    private static RequestSpecification requestSpec;

    @BeforeAll
    void setUp() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
    }

    @Test
    void getListOfUsersPage1() {
        given().spec(requestSpec).when().get("api/users?page=1").then().statusCode(200).contentType(ContentType.JSON).body("data.id", contains(1, 2, 3, 4, 5, 6)).body("data.first_name", contains("George", "Janet", "Emma", "Eve", "Charles", "Tracey"));
    }

    @Test
    void getListOfUsersPage2() {
        given().spec(requestSpec).when().get("api/users?page=2").then().statusCode(200).contentType(ContentType.JSON).body("data.id", contains(7, 8, 9, 10, 11, 12)).body("data.first_name", contains("Michael", "Lindsay", "Tobias", "Byron", "George", "Rachel"));
    }

    @Test
    void userNotFound() {
        given().spec(requestSpec).when().get("api/users/23").then().statusCode(404);
    }


}
