package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.*;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.database.Sql;
import com.automationteststore.helperutilities.date.DataSettings;
import com.automationteststore.restcalls.HTTPClientPost;
import com.automationteststore.restcalls.HTTPClientPut;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;


public class HTTPAPIRequestStepDefs {
    private ProcessFileResouce processFileResouce;
    private JSONObject jsonObject;
    private boolean bPost = false;
    private HTTPClientPost clientPost;
    private HttpResponse httpResponse;
    private String endpoint;
    private String username;
    private String password;
    private HTTPClientPut clientPut = new HTTPClientPut();

    @Before(value = "@systemTest,@uiTesting", order = 0)
    public void testStart() throws Throwable {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);


    }

    @Before(value = "@uiTesting", order = 1)
    public void userSetup() throws IOException, JSONException {
        // Change password if required

        JSONObject jsonObjectChangePassword = new JSONObject("{\n" + "  \"newPassword\": \"NEWp4s55w0rd\",\n"
                + "  \"password\": \"p4s55w0rd\",\n" + "  \"username\": \"michael.conway1@nhs.net\"\n" + "}");
        jsonObjectChangePassword.put("newPassword", "password");
        jsonObjectChangePassword.put("password", "m4sterpassw0rd?");
        jsonObjectChangePassword.put("username", "master.admin@nhs.net");
        httpResponse = clientPut.httpPutClient(GlobalVarsHelper.getInstance().getURL()
                + "administrator-gateway/administrator-auth/api/administrator/account/password", jsonObjectChangePassword);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            Assert.assertEquals(200, clientPut.getResponseCode());
        } else {
//			Assert.assertEquals("Something has gone wrong", 400, httpResponse.getStatusLine().getStatusCode());
        }

        // Authentication of the admin user
        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        endpoint = "administrator-gateway/administrator-auth/api/administrator/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employeeauthrequest");
        bPost = true;
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
//		Assert.assertEquals(200, clientPost.getResponseCode());

        // Creation of the user
        endpoint = "administrator-gateway/administrator-auth/api/administrator/register";
        jsonObject = processFileResouce.getNamedJSONObject("userRegistrationTestData");
        bPost = false;
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
//		Assert.assertEquals(201, clientPost.getResponseCode());

    }

    @Given("^I use the data file \"([^\"]*)\" and the endpoint \"([^\"]*)\"$")
    public void iUseTheDataFileAndTheEndpoint(String arg0, String arg1) throws Throwable {
        processFileResouce = new ProcessFileResouce(arg0);
        endpoint = arg1;
    }

    @When("^I use test data \"([^\"]*)\"$")
    public void iUseTestData(String arg0) throws Throwable {
        jsonObject = processFileResouce.getNamedJSONObject(arg0);
    }

    @And("^I use request method \"([^\"]*)\"$")
    public void iUseRequestMethod(String arg0) throws Throwable {
        if (arg0.toLowerCase().contains("post"))
            bPost = true;
        else
            bPost = false;
    }

    @And("^With authentication \"([^\"]*)\"$")
    public void withAuthentication(String arg0) throws Throwable {
        GlobalVarsHelper.getInstance().setUseAuthcode(Boolean.valueOf(arg0));
    }

    @Then("^The response should be \"([^\"]*)\" and \"([^\"]*)\"$")
    public void theResponseShouldBeAnd(String arg0, String arg1) throws Throwable {
        if (bPost) {
            clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
            Assert.assertEquals(arg0, String.valueOf(clientPost.getResponseCode()));
        }
    }

    @Given("^The \"([^\"]*)\" password needs changing using the initial password \"([^\"]*)\" using endpoint \"([^\"]*)\"$")
    public void thePasswordNeedsChangingUsingTheInitialPasswordUsingEndpoint(String arg0, String arg1, String arg2)
            throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        endpoint = arg2;
        username = arg0;
        password = arg1;
    }

    @When("^I change the password to \"([^\"]*)\"$")
    public void iChangeThePasswordTo(String arg0) throws Throwable {
        JSONObject jsonObjectChangePassword = new JSONObject("{\n" + "  \"newPassword\": \"NEWp4s55w0rd\",\n"
                + "  \"password\": \"p4s55w0rd\",\n" + "  \"username\": \"michael.conway1@nhs.net\"\n" + "}");

        jsonObjectChangePassword.put("newPassword", arg0);
        jsonObjectChangePassword.put("password", password);
        jsonObjectChangePassword.put("username", username);

        httpResponse = clientPut.httpPutClient(GlobalVarsHelper.getInstance().getURL() + endpoint,
                jsonObjectChangePassword);
    }

    @Then("^The user is ready$")
    public void theUserIsReady() throws Throwable {
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            Assert.assertEquals(200, String.valueOf(clientPut.getResponseCode()));
        } else {
            Assert.assertEquals(400, httpResponse.getStatusLine().getStatusCode(), "Something has gone wrong");
        }
    }

    @After("@systemTest,@uiTesting")
    public void clearTestData() throws Throwable {
        DataSettings data = loadDatabaseDetails();
        Connect c = new Connect();
        c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection conn = c.getConnection();
        Sql sql = new Sql(conn);
        sql.select(
                "DELETE FROM employer_user_role WHERE employer_user_id IN (SELECT ID FROM employer_user WHERE username = 'admin@admin.com')");
        sql.select(
                "DELETE FROM employer_details WHERE employer_user_id IN (SELECT ID FROM employer_user WHERE username = 'admin@admin.com')");
        sql.select("DELETE FROM employer_user WHERE username = 'admin@admin.com'");
        conn.close();

        // clearing down vacancy data
        Connect vacancyConnect = new Connect();
        vacancyConnect.setConnection(data.getVacancyDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection vacancyConnection = vacancyConnect.getConnection();
        Sql vacancySql = new Sql(vacancyConnection);
        vacancySql.select("DELETE FROM vacancy_employer");
        vacancySql.select("DELETE FROM job_advert");
        vacancySql.select("DELETE FROM vacancy_contact");
        vacancySql.select("DELETE FROM vacancy_details");
        vacancySql.select("DELETE FROM vacancy_skill");
        vacancySql.select("DELETE FROM vacancy");
        vacancyConnection.close();

        // clearing job advert data
        Connect jobAdvertConnect = new Connect();
        jobAdvertConnect.setConnection(data.getJobadvertDatabase(), data.getDatabaseusername(),
                data.getDatabasepassword(), data.getUrl());
        Connection jobadvertConnection = jobAdvertConnect.getConnection();
        Sql jobAdvertSql = new Sql(jobadvertConnection);
        jobAdvertSql.select("DELETE FROM job_advert_skill");
        jobAdvertSql.select("DELETE FROM job_advert");
        jobadvertConnection.close();
    }

    @After("@uiCandidateTesting")
    public void clearCandidateTestData() throws Throwable {
        //SetUpHelper.clearCandidateTestData();
    }

    private DataSettings loadDatabaseDetails() throws IOException {

        Yaml yaml = new Yaml();
        InputStream in = Connect.class.getResourceAsStream("/jsonFiles/application.yml");
        DataSettings data = yaml.loadAs(in, DataSettings.class);
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my.properties");
        java.util.Properties properties = new Properties();
        properties.load(inputStream);
        data.setUrl(properties.getProperty("database.dburl"));
        data.setDatabaseusername(properties.getProperty("database.username"));
        data.setDatabasepassword(properties.getProperty("database.password"));
        return data;
    }
}
