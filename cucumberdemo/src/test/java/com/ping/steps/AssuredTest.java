package com.ping.steps;

import com.ping.steps.AssuredPractice;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import lombok.extern.java.Log;
import org.junit.Assert;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.Map;
@Log
public class AssuredTest {
    AssuredPractice assuredPractice;
    Response response=null;
    public AssuredTest(){
        assuredPractice=AssuredPractice.getInstance();
    }

    @When("^I get the book with asin \"([^\"]*)\"$")
    public void iGetTheBookWithAsin(String asin) throws Throwable {
    }

    @Then("^state code will be \"([^\"]*)\"$")
    public void state_code_will_be(int statusCode) throws Throwable {
       response.then().assertThat().statusCode(statusCode);
    }

    @And("^response includes the following data$")
    public void responseIncludesTheFollowingData(Map<String,String> responField) {
        for(Map.Entry<String,String> field:responField.entrySet())
        {
            Response response = null;
            response.then().assertThat().body(field.getKey(),equalTo(field.getValue()));
        }

    }


    @When("^I send a get request with url \"([^\"]*)\"$")
    public void iSendAGetRequestWithUrl(String arg0) throws Throwable {
        response=assuredPractice.post(arg0);
        log.info("log: "+response.toString());
    }

    @Then("^the response status should be \"([^\"]*)\"$")
    public void theResponseStatusShouldBe(int  statuscode) throws Throwable {
        Object jsonResponse=response.getStatusCode();
        log.info("response: "+jsonResponse);
        Assert.assertEquals(jsonResponse,statuscode);
        log.info("log:status code is 200 right");
    }

    @Then("^the JSON response \"(.*?)\" equals \"(.*?)\"$")
    public void the_JSON_response_equals(String jsonPath, String expected) throws Throwable {
        String jsonValue=assuredPractice.getJsonPathValue(jsonPath);
        Assert.assertEquals(jsonValue,expected);
    }


    @When("^I send a POST request to \"([^\"]*)\" and request json:$")
    public void i_send_a_POST_request_to_and_request_json(String path,String json) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response=assuredPractice.post(path,json);
    }

    @When("^I get book with \"([^\"]*)\" and \"([^\"]*)\"$")
    public Response  i_get_book_with_and(int id, String name) throws Throwable {
        response=given().spec(assuredPractice.getRequestSpecification()).log().all().param("id",id).param("name",name).when().get("/byparam");
        return response;
    }


    @Given("^set base url and set header$")
    public void set_base_url_and_set_header(String header) {
        assuredPractice.setUrlAndHeaders("http://localhost:8080",header);
    }

    @Then("^response code should be (\\d+) and header should be:$")
    public void response_code_should_be_and_header_should_be(int statuscode,String responseHeader) {
        response.then().spec(assuredPractice.checkResult(statuscode,responseHeader)).time(lessThan(1000L)).log().all();
    }
}
