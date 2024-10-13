package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.ProcessFileResouce;
import com.automationteststore.restcalls.HTTPClientGet;
import com.automationteststore.restcalls.HTTPClientPost;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class GenericMicroServiceTestSteps {

    private HTTPClientPost httpClientPost;
    private HTTPClientGet httpClientGet;
    private boolean bPost = false;
    private String endpoint;
    private String method;
    private ProcessFileResouce fileResouce;
    private ProcessFileResouce responseFile;

    @Given("^I pass the \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iPassTheAnd(String arg0, String arg1) throws Throwable {
        fileResouce = new ProcessFileResouce(arg1);
        endpoint = arg0;
    }

    @And("^use the \"([^\"]*)\"$")
    public void useThe(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        method = arg0;
    }

    @When("^I process the call$")
    public void iProcessTheCall() throws Throwable {
        if (method.toLowerCase().contains("post")) {
            httpClientPost = new HTTPClientPost(endpoint, fileResouce.getJsonObject());
            System.out.print(httpClientPost.getContent());
            bPost = true;
        } else if (method.toLowerCase().contains("get")) {
            httpClientGet = new HTTPClientGet(endpoint);
            System.out.print(httpClientGet.getResponseOutput());
        }
    }

    @Then("^The response should be \"([^\"]*)\" with \"([^\"]*)\"$")
    public void theResponseShouldBeWith(String arg0, String arg1) throws Throwable {

        if (bPost) {
            responseFile = new ProcessFileResouce(arg0);
            Assert.assertEquals(String.valueOf(httpClientPost.getResponseCode()), arg1);
            Assert.assertEquals(responseFile.getJsonObject().toString(), httpClientPost.getContent());
        } else {
            responseFile = new ProcessFileResouce(arg0);
            Assert.assertEquals(responseFile.getJsonObject().toString(), httpClientGet.getResponseOutput());
            Assert.assertEquals(String.valueOf(arg1), String.valueOf(httpClientGet.getResponseCode()));
        }
    }

}
