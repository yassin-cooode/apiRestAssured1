package newApiStart;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class RegisterAndLogin {
    private static RequestSpecification requestSpec;
    private static String token;
    private static final String newUserRequest = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"pistol\"\n" +
            "}";


    @BeforeAll
    void setUp() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
    }

    @Test
    @Order(1)
    void registerNewUser() {
        Response response = given().spec(requestSpec).contentType(ContentType.JSON).body(newUserRequest).when().post("api/register");

        token = response.path("token");

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    @Order(2)
    void loginSuccessfully() {

        given().spec(requestSpec).contentType(ContentType.JSON).body(newUserRequest).when().post("api/login").then().statusCode(200).body("token" , equalTo(token));
    }
}
