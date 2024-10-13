package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.ProcessFileResouce;
import com.automationteststore.restcalls.HTTPClientPost;
import com.automationteststore.restcalls.HTTPClientPut;
import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

public class TestSetup {
    private ProcessFileResouce processFileResouce;
    private JSONObject jsonObject;
    private boolean bPost = false;
    private String endpoint;
    private HTTPClientPost clientPost;
    private HttpResponse httpResponse;
    private HTTPClientPut clientPut = new HTTPClientPut();
    private Properties properties = new Properties();

    public void changePasswordForMasterAdmin() throws JSONException, IOException {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        JSONObject jsonObjectChangePassword = new JSONObject("{\n" + "  \"newPassword\": \"NEWp4s55w0rd\",\n"
                + "  \"password\": \"p4s55w0rd\",\n" + "  \"username\": \"michael.conway1@nhs.net\"\n" + "}");
        jsonObjectChangePassword.put("newPassword", "password");
        jsonObjectChangePassword.put("password", "m4sterpassw0rd?");
        jsonObjectChangePassword.put("username", "master.admin@nhs.net");
        httpResponse = clientPut.httpPutClient(GlobalVarsHelper.getInstance().getURL()
                + "employer-gateway/employer-auth/api/employer/account/password", jsonObjectChangePassword);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            Assert.assertEquals(200, clientPut.getResponseCode());
        } else {
            Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), 400, "Something has gone wrong");
        }
    }

    public void createUser(String email) throws IOException, JSONException {
        // Authentication of the admin user
        processFileResouce = new ProcessFileResouce("storydatafile.json");
        GlobalVarsHelper.getInstance().setURL(this.properties.getProperty("microservicebase.uri"));
        endpoint = "employer-gateway/employer-auth/api/employer/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employeeauthrequest");
        bPost = true;
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        Assert.assertEquals(200, clientPost.getResponseCode());

        // Creation of the user
        endpoint = "employer-gateway/employer-auth/api/employer/register";
        jsonObject = processFileResouce.getNamedJSONObject("userRegistrationTestData");
        jsonObject.put("admin@admin.com", email);

        bPost = false;
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        Assert.assertEquals(201, clientPost.getResponseCode());
    }

    @Before
    public void loadPropertiesForTestRun() throws IOException, SQLException, JSONException {
        //properties = loadProperties("my.properties");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("uitesting.url"));
        GlobalVarsHelper.getInstance().setCandidateURL(properties.getProperty("candidateuitesting.url"));

    }

    private Properties loadProperties(String propertiesResourceName) throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(propertiesResourceName);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }


    @After
    public void exitInstance(Scenario scenario) throws IOException, SQLException {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) WebDrv.getInstance().getWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (WebDriverException e) {
                throw new WebDriverException(e.getMessage());
            }
        }

        WebDrv.getInstance().getWebDriver().quit();
        WebDrv.setInstance();

    }

    @After
    public void endOfScenarioStatus(Scenario scenario) {
        String featureName = "Feature :";
        String rawFeatureName = scenario.getId().split(";")[0].replace("-", " ");
        featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
        System.out.println(featureName);
        System.out.println("Scenario name : " + scenario.getName() + " Status: " + scenario.getStatus() + " Date and Time : " + LocalDateTime.now());
    }

}
