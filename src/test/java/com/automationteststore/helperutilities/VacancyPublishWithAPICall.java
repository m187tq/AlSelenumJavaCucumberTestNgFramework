package com.automationteststore.helperutilities;

import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.date.DataSettings;
import com.automationteststore.restcalls.HTTPClientPost;
import com.automationteststore.restcalls.HTTPClientPut;
import com.automationteststore.testData.admin.AdminUITestData;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class VacancyPublishWithAPICall {

    public static ProcessFileResouce processFileResouce;
    public static JSONObject jsonObject;
    public static boolean bPost = false;
    public static String endpoint;
    public static HTTPClientPost clientPost;
    public static HttpResponse httpResponse;
    public static HTTPClientPut clientPut = new HTTPClientPut();
    public static Properties properties = new Properties();
    public static DataSettings data;
    public static int employerUserId;
    public static String employerCode;
    public static String guid;
    public static String job_reference;
    public static int vacancyID;
    public static int version;


    static {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String createADraftVacancyAndPublish() throws IOException, JSONException, SQLException {
        /**** GetValue Authorisation token from administrator auth authentication using administrator user credentials *****/
        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("microservicebase.uri"));
        endpoint = "administrator-gateway/administrator-auth/api/administrator/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employerUserAuthentication");
        bPost = true;
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);

        if (clientPost.getResponseCode() == 200) {
            System.out.println("Authenticated Employer User");
            Assert.assertEquals(200, clientPost.getResponseCode());
        } else {
            System.out.println("Unable to Authenticate Employer User " + clientPost.getResponseCode());
            Assert.assertTrue(false);
        }

        /**** Create Draft Vacancy API Call *****/

        processFileResouce = new ProcessFileResouce("jsonFiles/vacancycreationapirequest.json");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("microservicebase.uri"));
        endpoint = "/administrator-gateway/vacancy/api/vacancy";
        jsonObject = processFileResouce.getNamedJSONObject("vacancyRequest");

        employerUserId = getEmployerUserID();

        JSONObject panelObj = (JSONObject) jsonObject.get("shortlistingPanel");
        panelObj.put("leadId", employerUserId);

        httpResponse = clientPut.httpPutClient(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);

        if (httpResponse.getStatusLine().getStatusCode() == 201) {
            Assert.assertEquals(201, clientPut.getResponseCode());
            System.out.println("Draft Vacancy Created");

            HttpEntity entity = httpResponse.getEntity();
            JSONObject responseObject = new JSONObject(EntityUtils.toString(entity));
            job_reference = responseObject.getString("jobReference");
            vacancyID = responseObject.getInt("id");
            version = responseObject.getInt("version");


        } else {
            System.out.println("Unable to create Draft Vacancy " + clientPut.getResponseCode());
            Assert.assertTrue(false);
        }

        /**** Publish Vacancy API Call *****/

        JSONObject publishAdvertObj = new JSONObject();
        publishAdvertObj.put("id", vacancyID);
        publishAdvertObj.put("version", version);

        endpoint = "administrator-gateway/vacancy/api/vacancy/publish";
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, publishAdvertObj);

        if (clientPost.getResponseCode() == 200) {
            System.out.println("Job Advert published successfully");
            Assert.assertEquals(200, clientPost.getResponseCode());
        } else {
            System.out.println("Unable to publish job advert " + clientPost.getResponseCode());
            Assert.assertTrue(false);
        }

        return job_reference;
    }

    public static int getEmployerUserID() throws IOException, SQLException {
        data = loadDatabaseDetails();
        Connect c = new Connect();
        c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection conn = c.getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt
                .executeQuery("SELECT id FROM employer_user WHERE email_address = '" + AdminUITestData.EXISTING_USER_EMAIL_ADDRESS + "'");

        int empUserId = 0;

        while (rs.next()) {
            empUserId = rs.getInt("id");
        }

        conn.close();
        return empUserId;
    }

    public static DataSettings loadDatabaseDetails() throws IOException {
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
