package brands;

import apitesting.util.ApiMethods;
import apitesting.util.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class BrandsStepDefs {
    private Response response;
    private String expectedBrandName;

    @Given("the API endpoint brands is available")
    public void theAPIEndpointBrandsIsAvailable() {
    }

    @When("I send a GET to endpoint request to brands")
    public void iSendAGETToRequestToBrands() {
        response = ApiMethods.get(Config.getBaseUri(),Config.getEndpoint());
    }

    @Then("I should receive a {string} response")
    public void iShouldReceiveAResponse(String expectedStatus) {
        int actualStatusCode = response.getStatusCode();
        int expectedStatusCode = Integer.parseInt(expectedStatus);
        assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status: " + expectedStatus + " but got: " + actualStatusCode);

    }

    @Then("Check that the list of brands has more than one entry.")
    public void checkThatTheListOfBrandsHasMoreThanOneEntry() {
        List<Object> brands = response.jsonPath().getList("$");
        Assert.assertTrue(brands.size() > 1, "Expected more than one brand entry, but found: " + brands.size());
        if (brands.size() > 1) {
            System.out.println("brands has more than one entery");
        }

    }

    @Then("Check that each brand object contains _id and name properties.")
    public void checkThatEachBrandObjectContains_idAndNameProperties() {
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> brands = jsonPath.getList("$");
        for (Map<String, Object> brand : brands) {
            Assert.assertTrue(brand.containsKey("_id"), "Brand object missing _id");
            Assert.assertTrue(brand.containsKey("name"), "Brand object missing name");
        }
    }

    @When("I send a GET to endpoint request to return specific brand with ID {string}")
    public void iSendAGETToEndpointRequestToReturnSpecificBrandWithID( String id) {
        response = ApiMethods.get(Config.getBaseUri(),Config.getEndpoint()+id);
    }


    @Then("The brand name should match the {string}")
    public void theBrandNameShouldMatchThe(String expectedBrandName) {
        this.expectedBrandName = expectedBrandName;
        String actualBrandName = response.jsonPath().getString("name");
        Assert.assertEquals(actualBrandName,expectedBrandName , "Brand name does not match"+expectedBrandName);
    }

    @When("I send a post request with name {string} and description {string}")
    public void iSendAPostRequestWithNameAndDescription(String name, String description) {
        String requestBody = "{ \"name\": \"" + name + "\", \"description\": \"" + description + "\" }";
        response = ApiMethods.post(Config.getEndpoint() ,requestBody );
    }

    @Then("error message {string} should be returned")
    public void errorMessageShouldBeReturned(String message ) {
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assert.assertEquals(responseBody , message);
    }


    @When("I send a Delete to endpoint request to return specific brand with ID {string}")
    public void iSendADeleteToEndpointRequestToReturnSpecificBrandWithID(String id) {
        response = ApiMethods.delete(Config.getBaseUri(),Config.getEndpoint()+id);

    }
}