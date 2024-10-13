package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.ProcessFileResouce;
import com.automationteststore.restcalls.HTTPClientPost;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

/*
    "userRegistrationTestData": {
      "companyName": "Whickham Cottage Practice",
      "firstName": "Fred",
      "organisationRole": "Practice Manager",
      "password": "p4s55w0rd",
      "roles": [
        "ROLE_ADMIN"
      ],
      "surname": "Sploogan",
      "username": "fred.sploogan@nhs.net"
    }
 */

public class NegativeScenarioEMPRegisterAPIStepDefs {
    private ProcessFileResouce processFileResouce;
    private JSONObject updatedJson;
    private HTTPClientPost clientPost;
    private String endpoint;

    @Given("^I use the data file \"([^\"]*)\" with the end point \"([^\"]*)\"$")
    public void iUseTheDataFileWithTheEndPoint(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        processFileResouce = new ProcessFileResouce(arg0);
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        endpoint = arg1;
    }

    @And("^I use the test data set \"([^\"]*)\"$")
    public void iUseTheTestDataSet(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        updatedJson = processFileResouce.getNamedJSONObject(arg0);
    }

    @And("^I use the username \"([^\"]*)\"$")
    public void iUseTheUsername(String arg0) throws Throwable {
        updatedJson = processFileResouce.changeAttribute(updatedJson, "username", arg0);
    }

    @And("^I use the password \"([^\"]*)\"$")
    public void iUseThePassword(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        updatedJson = processFileResouce.changeAttribute(updatedJson, "password", arg0);
    }

    @And("^I use the roles \"([^\"]*)\"$")
    public void iUseTheRoles(String arg0) throws Throwable {
        JSONArray roles = new JSONArray("[" + arg0 + "]");
        updatedJson.put("roles", roles);
    }

    @And("^I use the company name\"([^\"]*)\"$")
    public void iUseTheCompanyName(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        updatedJson = processFileResouce.changeAttribute(updatedJson, "companyName", arg0);
    }

    @And("^I use the surname \"([^\"]*)\"$")
    public void iUseTheSurname(String arg0) throws Throwable {
        updatedJson = processFileResouce.changeAttribute(updatedJson, "surname", arg0);
    }

    @And("^I use the first name\"([^\"]*)\"$")
    public void iUseTheFirstName(String arg0) throws Throwable {
        updatedJson = processFileResouce.changeAttribute(updatedJson, "firstName", arg0);
    }

    @When("^I make the API Call$")
    public void iMakeTheAPICall() throws Throwable {
        System.out.println(updatedJson.toString());
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, updatedJson);
    }

    @Then("^The response call result should be \"([^\"]*)\"$")
    public void theResponseCallResultShouldBe(String arg0) throws Throwable {
        Assert.assertEquals(arg0, String.valueOf(clientPost.getResponseCode()));

    }

}
