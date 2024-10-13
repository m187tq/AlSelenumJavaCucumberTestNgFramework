package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.ProcessFileResouce;
import com.automationteststore.restcalls.HTTPClientPost;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;

public class NegativeTestingEmployerAuthStepDefs {

    private ProcessFileResouce processFileResouce;
    private JSONObject updatedJson;
    private HTTPClientPost clientPost;
    private String endpoint;

    @Given("^I user the data file \"([^\"]*)\" for testing with the end point \"([^\"]*)\"$")
    public void iUserTheDataFileForTestingWithTheEndPoint(String arg0, String arg1) throws Throwable {
        processFileResouce = new ProcessFileResouce(arg0);
        endpoint = arg1;
    }

    @And("^I use the test dataset \"([^\"]*)\"$")
    public void iUseTheTestDataset(String arg0) throws Throwable {
        updatedJson = processFileResouce.getNamedJSONObject(arg0);
    }

    @And("^I use username parameter \"([^\"]*)\"$")
    public void iUseUsernameParameter(String arg0) throws Throwable {

        updatedJson = processFileResouce.changeAttribute(updatedJson, "username", arg0);
    }

    @And("^I use password parameter \"([^\"]*)\"$")
    public void iUsePasswordParameter(String arg0) throws Throwable {
        updatedJson = processFileResouce.changeAttribute(updatedJson, "password", arg0);
    }

    @When("^I make the API end point call$")
    public void iMakeTheAPIEndPointCall() throws Throwable {
        System.out.println(updatedJson.toString());
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, updatedJson);
    }

    @Then("^The Response call should be of value \"([^\"]*)\"$")
    public void theResponseCallShouldBeOfValue(String arg0) throws Throwable {
        Assert.assertEquals(arg0, String.valueOf(clientPost.getResponseCode()));
    }
}
