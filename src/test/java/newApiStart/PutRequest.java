package newApiStart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PutRequest {
    private static RequestSpecification requestSpec;
    @BeforeAll
    void setUp() {
        requestSpec = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
    }

    @Test
    void updateUser2() throws JsonProcessingException {
        PostRequestBody putBodyObj = new PostRequestBody("morpheus" , "zion resident");
        ObjectMapper objectMapper = new ObjectMapper();

        String putBodyJson = objectMapper.writeValueAsString(putBodyObj);

        given().spec(requestSpec).contentType(ContentType.JSON).body(putBodyJson).when().put("api/users/2").then().statusCode(200).contentType(ContentType.JSON).body("name" , equalTo(putBodyObj.getName())).body("jop" , equalTo(putBodyObj.getJop()));
    }
}
