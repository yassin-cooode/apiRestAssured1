package newApiStart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostRequests {

    private static RequestSpecification requestSpec;

    @BeforeAll
    void setUp() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
    }

    @Test
    void postAnewUser() throws JsonProcessingException {
        PostRequestBody postObj = new PostRequestBody("yassin", "Tester");
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonRequestBody = objectMapper.writeValueAsString(postObj);


        given().spec(requestSpec).contentType(ContentType.JSON).body(jsonRequestBody).when().post("api/users").then().statusCode(201).contentType(ContentType.JSON).body("name", equalTo("yassin")).body("jop", equalTo("Tester"));
    }

    // In this example serialization and deserialization are utilized
    @Test
    void post_a_new_user() throws JsonProcessingException {

        PostRequestBody postObj = new PostRequestBody("yassin", "Tester");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequestBody = objectMapper.writeValueAsString(postObj);

        Response response = given().spec(requestSpec).contentType(ContentType.JSON).body(jsonRequestBody).when().post("api/users");

        PostRequestBody returnedPostAsObj = objectMapper.readValue(response.getBody().asString(), PostRequestBody.class);

        Assertions.assertAll(
                () -> Assertions.assertEquals("yassin", postObj.getName()),
                () -> Assertions.assertEquals("Tester", postObj.getJop())
        );
    }
}
