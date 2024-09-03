package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinations extends Utils {
    RequestSpecification res;
    Response response;
    ResponseSpecification resSpec;
    TestDataBuild data = new TestDataBuild();
    static String place_id;

    @Given("Add Place Payload with {string},{string},{string}")
    public void add_place_payload_with(String name,String language,String location) throws IOException {
        res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,location));
    }
    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource,String method) {
        APIResource resourceAPI = APIResource.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        resSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResource());
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(int status) {
        assertEquals(response.getStatusCode(),status);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @Then("verify placeId created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String actualName = getJsonPath(response,"name");
        assertEquals(expectedName,actualName);
    }

    @Given("Delete Place Payload")
    public void delete_place_payload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
