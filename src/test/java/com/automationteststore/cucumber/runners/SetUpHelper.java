package com.automationteststore.cucumber.runners;

import com.automationteststore.helperutilities.*;
import com.automationteststore.helperutilities.GlobalVars.GlobalVarsHelper;
import com.automationteststore.helperutilities.database.Sql;
import com.automationteststore.helperutilities.date.DataSettings;
import com.automationteststore.restcalls.HTTPClientPost;
import com.automationteststore.restcalls.HTTPClientPut;
import com.automationteststore.testData.admin.AdminUITestData;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SetUpHelper {
    public static ProcessFileResouce processFileResouce;
    public static JSONObject jsonObject;
    public static boolean bPost = false;
    public static String endpoint;
    public static HTTPClientPost clientPost;
    public static HttpResponse httpResponse;
    public static HTTPClientPut clientPut = new HTTPClientPut();
    public static Properties properties = new Properties();
    public static DataSettings data;
    public static int employerId;
    public static String employerCode;
    public static ArrayList<String> job_reference = new ArrayList<>();
    public static ArrayList<Integer> reference_id = new ArrayList<>();
    public static ArrayList<Integer> vacancyID = new ArrayList<>();
    public static String guid;

    static {
        java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("my.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void changePassword(String userName, String password, String newPassword)
            throws JSONException, IOException, SQLException {
        if (getFirstTimeLoginFlag(userName)) {
            JSONObject jsonObjectChangePassword = new JSONObject("{\n" + "  \"newPassword\": \"NEWp4s55w0rd\",\n"
                    + "  \"password\": \"Password1\",\n" + "  \"username\": \"michael.conway1@nhs.net\"\n" + "}");

            jsonObjectChangePassword.put("newPassword", newPassword);
            jsonObjectChangePassword.put("password", password);
            jsonObjectChangePassword.put("username", userName);

            httpResponse = clientPut.httpPutClient(GlobalVarsHelper.getInstance().getURL()
                    + "administrator-gateway/administrator-auth/api/administrator/account/password", jsonObjectChangePassword);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                Assert.assertEquals(200, clientPut.getResponseCode());
                System.out.println("Password changed for: " + userName);
            } else {
                String response = EntityUtils.toString(httpResponse.getEntity());

                JSONObject jsonObj = new JSONObject(response);

                JSONArray arr = jsonObj.getJSONArray("errors");

                System.out.println("Unable to change Password for: " + userName + ": " + arr.getJSONObject(0).getString("description"));
                System.out.println("Status code is: " + clientPut.getResponseCode());

                Assert.assertTrue(false);
            }
        } else {

            System.out.println(userName + " Not first time login");
        }
    }

    public static void createUser() throws IOException, JSONException, SQLException {
        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("microservicebase.uri"));
        endpoint = "administrator-gateway/administrator-auth/api/administrator/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employeeauthrequest");
        System.out.println(jsonObject);
        System.out.println(GlobalVarsHelper.getInstance().getURL() + endpoint);
        bPost = true;
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        // Assert.assertEquals(200, clientPost.getResponseCode());

        if (clientPost.getResponseCode() == 200) {
            System.out.println("Authenticated NHSBSA ");
            Assert.assertEquals(200, clientPost.getResponseCode());
        } else {
            System.out.println("Unable to Authenticate NHSBSA " + clientPost.getResponseCode());
            Assert.assertTrue(false);
        }


        /**** Creation of the Employer *****/
        endpoint = "administrator-gateway/administrator-auth/api/administrator/register";
        jsonObject = processFileResouce.getNamedJSONObject("employerRegistrationTestData");
        bPost = false;
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        employerCode = jsonObject.getString("employerCode");
        employerId = getEmployerID(employerCode);
        if (clientPost.getResponseCode() == 201) {
            System.out.println("Created Employer: ");
            Assert.assertEquals(201, clientPost.getResponseCode());
        } else {
            String response = clientPost.getContent();

            JSONObject jsonObj = new JSONObject(response);

            JSONArray arr = jsonObj.getJSONArray("errors");

            System.out.println("Unable to Create Employer: " + arr.getJSONObject(0).getString("description"));

            Assert.assertTrue(false);
        }

        /**** Creation of the Admin / GP user *****/
        endpoint = "administrator-gateway/administrator-auth/api/administrator/register/user";
        JSONObject jsonObject = new JSONObject(
                "{\n" + "\"roles\": [\"ROLE_SUPER_USER\"],\n" + " \"firstName\": \"Admin\",\n" + " \"lastName\": \"Surname\",\n"
                        + " \"jobTitle\": \"Practice Manager\",\n " + " \"emailAddress\": \"admin@admin.com\",\n "
                        + " \"password\": \"Password1234\",\n " + " \"employerId\": " + employerId + " \n" + "}");
        bPost = false;
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);

        if (clientPost.getResponseCode() == 201) {
            System.out.println("Created User: ");
            Assert.assertEquals(201, clientPost.getResponseCode());
        } else {
            String response = clientPost.getContent();

            JSONObject jsonObj = new JSONObject(response);

            JSONArray arr = jsonObj.getJSONArray("errors");

            System.out.println("Unable to Create User: " + arr.getJSONObject(0).getString("description"));

            Assert.assertTrue(false);
        }

        /**** Change password *****/
        changePassword("admin@admin.com", "Password1234", "Password1234");
    }

    public static boolean getFirstTimeLoginFlag(String emailAddress) throws IOException, SQLException {
        data = loadDatabaseDetails();
        Connect c = new Connect();
        c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection conn = c.getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT credentials_expired FROM employer_user WHERE email_address = '" + emailAddress + "'");

        boolean firstTimeLogin = true;

        while (rs.next()) {
            firstTimeLogin = rs.getBoolean("credentials_expired");
        }

        conn.close();
        return firstTimeLogin;
    }


    public static int getEmployerID(String employerCode) throws IOException, SQLException {
        data = loadDatabaseDetails();
        Connect c = new Connect();
        c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection conn = c.getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT id FROM employer WHERE employer_code = '" + employerCode + "'");

        int empId = 0;

        while (rs.next()) {
            empId = rs.getInt("id");
        }

        conn.close();
        return empId;
    }

    public static int getUserId(int employerId) throws IOException, SQLException {
        data = loadDatabaseDetails();
        Connect c = new Connect();
        c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection conn = c.getConnection();
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt
                .executeQuery("SELECT user_id FROM employer_to_employer_user WHERE employer_id = '" + employerId + "'");

        int user_id = 0;

        while (rs.next()) {
            user_id = rs.getInt("user_id");
        }

        conn.close();
        return user_id;
    }

    public static void clearShortlistandInterviewTestData() throws IOException, SQLException, InterruptedException {
        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        jsonObject = processFileResouce.getNamedJSONObject("employerRegistrationTestData");
        employerCode = jsonObject.getString("employerCode");
        employerId = getEmployerID(employerCode);
        data = loadDatabaseDetails();

        /***** clearing down Shortlisting data *****/
        Connect shortlistingConnect = new Connect();
        shortlistingConnect.setConnection(data.getShortlistingDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection shortlistingConnection = shortlistingConnect.getConnection();
        Statement stmt1 = shortlistingConnection.createStatement();
        Sql shortlistingSql = new Sql(shortlistingConnection);

        /****
         * Using the vacancy ids scoresheets are identified then
         * We get Application Reference No which is obtained from scoring table.
         * With that Application Reference number need to get
         * id, scoresheet_id, application_id, candidate_id from scoring table.
         *
         * With that id need to get
         * criterion_id from selected_criterion table
         *
         * With scoresheet_id need to get
         * vacancy_id from scoresheet table
         *
         * Then the deletion table order is
         * selected_criterion, skill_desirable, skill_essential
         * scoring, application
         * skill, shortlist_vacancy, scoresheet, criterion
         */
        System.out.println("Clearing down Shortlisting data");
        int scoring_id = 0, scoresheet_id = 0;
        List<Integer> criterion = new ArrayList<>();
        List<Integer> skills = new ArrayList<>();
        List<String> applicationReferences = new ArrayList<>();
        try {
            if (vacancyID.size() > 0) {
                for (int vacancy : vacancyID) {
                    ResultSet rs = stmt1.executeQuery(
                            "SELECT id FROM scoresheet WHERE vacancy_id = " + vacancy + ";");
                    while (rs.next()) {
                        scoresheet_id = rs.getInt("id");
                    }
                    // finding skill id and deletion of skill essential and desirable
                    rs = stmt1.executeQuery("SELECT id FROM skill WHERE scoresheet_id = " + scoresheet_id + ";");
                    while (rs.next()) {
                        skills.add(rs.getInt("id"));
                    }
                    //only applicable if there are any scorings done
                    rs = stmt1.executeQuery(
                            "SELECT id, scoresheet_id,application_reference FROM scoring WHERE scoresheet_id = '"
                                    + scoresheet_id + "'");
                    while (rs.next()) {
                        scoring_id = rs.getInt("id");
                        applicationReferences.add(rs.getString("application_reference"));
                        Statement stmt2 = shortlistingConnection.createStatement();
                        ResultSet selectedCriterionRS = stmt2.executeQuery(
                                "SELECT criterion_id FROM selected_criterion WHERE scoring_id = " + scoring_id + ";");
                        while (selectedCriterionRS.next()) {
                            criterion.add(selectedCriterionRS.getInt("criterion_id"));
                            shortlistingSql.select("DELETE FROM selected_criterion WHERE criterion_id = "
                                    + selectedCriterionRS.getInt("criterion_id") + " AND scoring_id = " + scoring_id + ";");
                        }
                    }
                    for (String applicationReference : applicationReferences) {
                        // deletion of scoring and application for each applications
                        shortlistingSql.select("DELETE FROM application WHERE application_reference = '" + applicationReference + "';");
                        shortlistingSql.select("DELETE FROM scoring WHERE application_reference = '" + applicationReference + "';");
                    }
                    // deletion of skill desirable and skill essential catering for scenarios where application is present and not present
                    for (int skill : skills) {
                        Statement stmt3 = shortlistingConnection.createStatement();
                        ResultSet criterionSkillEssentialRS = stmt3.executeQuery(
                                "SELECT criterion_id FROM skill_essential WHERE skill_id = " + skill + ";");
                        if (criterionSkillEssentialRS.next()) {
                            criterion.add(criterionSkillEssentialRS.getInt("criterion_id"));
                        }
                        ResultSet criterionSkillDesirableRS = stmt3.executeQuery(
                                "SELECT criterion_id FROM skill_desirable WHERE skill_id = " + skill + ";");
                        if (criterionSkillDesirableRS.next()) {
                            criterion.add(criterionSkillDesirableRS.getInt("criterion_id"));
                        }
                        shortlistingSql.select("DELETE FROM skill_essential WHERE skill_id = " + skill + ";");
                        shortlistingSql.select("DELETE FROM skill_desirable WHERE skill_id = " + skill + ";");
                    }
                    // deletion of skills for the scoresheet
                    shortlistingSql.select("DELETE FROM skill WHERE scoresheet_id = " + scoresheet_id + ";");
                }
                // deletion of all other shortlisting related table using vacancy ids
                for (int vacancy : vacancyID) {
                    shortlistingSql.select("DELETE FROM shortlisting_panelists WHERE shortlisting_panel_id in (select id from shortlisting_panel WHERE vacancy_id = " + vacancy + ");");
                    shortlistingSql.select("DELETE FROM shortlisting_panel WHERE vacancy_id = " + vacancy + ";");
                    shortlistingSql.select("DELETE FROM shortlist_vacancy WHERE vacancy_id = " + vacancy + ";");
                    shortlistingSql.select("DELETE FROM scoresheet WHERE vacancy_id = " + vacancy + ";");
                }
                // deletion of all criterion
                for (int id : criterion) {
                    shortlistingSql.select("DELETE FROM criterion WHERE id = " + id + ";");
                }
            }

            shortlistingConnection.close();

        } catch (Exception e) {
            System.out.println("Unable to clear down Shortlisting data with error :" + e.getMessage());
        }

        /***** clearing down Interview Panelist data *****/
        Connect interviewConnect1 = new Connect();
        interviewConnect1.setConnection(data.getInterviewDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection interviewConnection1 = interviewConnect1.getConnection();
        Statement interviewstmt1 = interviewConnection1.createStatement();
        Sql interviewSql1 = new Sql(interviewConnection1);

        /****
         * First we need interview panel ID.
         *
         * Then the deletion table is
         * panelist
         */
        System.out.println("Clearing down Interview Panelist data");

        try {
            ResultSet rs = interviewstmt1
                    .executeQuery("SELECT id FROM interview_panel WHERE interview_id in (SELECT ID FROM interview WHERE employer_code = '" + employerCode + "');");

            while (rs.next()) {
                interviewSql1.select("DELETE FROM panelist WHERE interview_panel_id = " + rs.getInt("id") + ";");
            }

            interviewConnection1.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down Interview panelist data with error :" + e.getMessage());
        }

        /***** clearing down Interview data *****/
        Connect interviewConnect = new Connect();
        interviewConnect.setConnection(data.getInterviewDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection interviewConnection = interviewConnect.getConnection();
        Statement interviewstmt = interviewConnection.createStatement();
        Sql interviewSql = new Sql(interviewConnection);

        /****
         * First we need Vacancy ID.
         * With that Vacancy ID we need to get id from interview table.
         *
         * Then the deletion table order is
         * ui_data, interview_panel, interview_candidate, slot, interview
         */
        System.out.println("Clearing down Interview data");

        try {
            ResultSet rs = interviewstmt
                    .executeQuery("SELECT id FROM interview WHERE employer_code = '" + employerCode + "';");

            while (rs.next()) {
                interviewSql.select("DELETE FROM ui_data WHERE interview_id = " + rs.getInt("id") + ";");
                interviewSql.select("DELETE FROM interview_panel WHERE interview_id = " + rs.getInt("id") + ";");
                interviewSql.select("DELETE FROM interview_candidate WHERE interview_id = " + rs.getInt("id") + ";");
                interviewSql.select("DELETE FROM slot WHERE interview_id = " + rs.getInt("id") + ";");
                interviewSql.select("DELETE FROM interview WHERE id = " + rs.getInt("id") + ";");
            }

            interviewConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down Interview data with error :" + e.getMessage());
        }
    }

    public static void clearTestData() throws IOException, SQLException, InterruptedException {
        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        jsonObject = processFileResouce.getNamedJSONObject("employerRegistrationTestData");
        employerCode = jsonObject.getString("employerCode");
        employerId = getEmployerID(employerCode);
        data = loadDatabaseDetails();

        /***** clearing down Employer-user and Employer data *****/
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Statement stmt = conn.createStatement();
            Sql sql = new Sql(conn);

            System.out.println("Clearing down Employer-user and Employer data");

            ResultSet rs = stmt
                    .executeQuery("SELECT user_id FROM employer_to_employer_user WHERE employer_id = '" + employerId + "'");

            while (rs.next()) {
                int userId = rs.getInt("user_id");

                sql.select("DELETE FROM employer_to_employer_user WHERE employer_id = " + employerId + "AND user_id ='" + userId + "';");
                sql.select("DELETE FROM employer_user WHERE id =" + userId + ";");
                sql.select("DELETE FROM atriskcandidates WHERE employer_id= '" + employerId + "'");
                sql.select("DELETE FROM accredited_logo WHERE employer_id= '" + employerId + "'");
            }

            sql.select("DELETE FROM administrator WHERE id = " + employerId + "AND employer_code ='" + employerCode + "';");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
        /***** clearing down vacancy and job advert data *****/

        clearVacancyAndJobAdvertdata();
        clearESRVacancyAndJobAdvertdata();
        clearShortlistandInterviewTestData();
        clearIssueContractdata();
        clearContractTemplatedata();
        clearOfferTemplatedata();
        clearOfferSupportingDocumentData();
        clearOfferSupportingLinkData();
        clearOfferPreEmploymentdata();
        clearOfferdata();
        clearWorkflowdata();

    }

/*    public static void clearCandidateTestData() throws IOException, SQLException {
        data = loadDatabaseDetails();

        *//**** clearing down Candidate Auth data ****//*
        System.out.println("clearing down customer auth data");
        try {
            Connect candidateAuthConnect = new Connect();
            candidateAuthConnect.setConnection(data.getCandidateDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection candidateAuthConn = candidateAuthConnect.getConnection();
            Statement stmt = candidateAuthConn.createStatement();
            Sql candSql = new Sql(candidateAuthConn);

            // These are the customer user names used to create accounts while applying for the job.
            // If new accounts are added, the details needs to be added to the following List.
            List<String> candidates = new ArrayList<>();
            candidates.add("automation@test.com");
            candidates.add("candidate1@gmail.com");
            candidates.add("candidate2@gmail.com");
            candidates.add("candidate3@gmail.com");
            candidates.add("candidate4@gmail.com");
            candidates.add("candidate5@gmail.com");
            candidates.add("candidate6@gmail.com");
            candidates.add("candidate7@gmail.com");

            for (String candidate : candidates) {
                ResultSet rs = stmt
                        .executeQuery("SELECT id  FROM candidate_user WHERE username = '" + candidate + "'");
                while (rs.next()) {
                    candSql.select("DELETE FROM candidate_profile WHERE candidate_user_id = " + rs.getInt("id") + ";");
                }

                candSql.select("DELETE FROM candidate_user WHERE username = '" + candidate + "'");
            }

            candidateAuthConn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down customer auth data with error :" + e.getMessage());
        }
        */

    /**** clearing down Application data ******//*
        System.out.println("clearing down Application data");
        try {
            Connect applicationConnect = new Connect();
            applicationConnect.setConnection(data.getApplicationDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection applicationConnection = applicationConnect.getConnection();
            Statement stmt2 = applicationConnection.createStatement();
            Sql applicationSql = new Sql(applicationConnection);

            String jobReferenceNumber = VacancyCreationStepsUtil.getJobReference();

            ResultSet rs = stmt2
                    .executeQuery("SELECT id  FROM application WHERE job_reference = '" + jobReferenceNumber + "'");
            while (rs.next()) {
                int application_id = rs.getInt("id");

                applicationSql.select("DELETE FROM skills WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM job_history WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM academic_qualifications WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM safeguarding WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM professional_training WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM immigration_status WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM equality_and_diversity WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM ui_data WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM gis_disability WHERE application_id = " + application_id + ";");
                applicationSql.select("DELETE FROM fitness_to_practise WHERE application_id in (" + application_id + ");");
                applicationSql.select("DELETE FROM application WHERE id = " + application_id + ";");
            }

            applicationConnection.close();

        } catch (Exception e) {
            System.out.println("Unable to clear down application data with error :" + e.getMessage());
        }

    }*/
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

    //TODO need to refactor such that this method is removed and the above method is parameterised
    public static void deleteEmployerFromEmployerUser(String employerEmail) throws IOException, SQLException {
        data = loadDatabaseDetails();
        Connect c = new Connect();
        c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection conn = c.getConnection();
        Sql sql = new Sql(conn);

        String checkEmployerExists = "DELETE FROM employer_user WHERE username = 'emailAddress'";
        checkEmployerExists = checkEmployerExists.replace("emailAddress", employerEmail);

        sql.select(checkEmployerExists);

        List<String> query = new ArrayList<>();

        String query_1 = "DELETE FROM employer_user_role WHERE employer_user_id IN (SELECT ID FROM employer_user WHERE username = 'emailAddress')";
        query.add(query_1.replace("emailAddress", employerEmail));

        String query_2 = "DELETE FROM employer_details WHERE employer_user_id IN (SELECT ID FROM employer_user WHERE username = 'emailAddress')";
        query.add(query_2.replace("emailAddress", employerEmail));

        String query_3 = "DELETE FROM employer_user WHERE username = 'emailAddress'";
        query.add(query_3.replace("emailAddress", employerEmail));

        for (int i = 0; i < query.size(); i++) {
            sql.select(query.get(i));
        }

        conn.close();

    }

    //TODO need to refactor such that this method is removed and the above method is parameterised
    public static void createUser(String email) throws IOException, JSONException, SQLException {
        /**** Authentication of the NHSBSA user *****/

        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("microservicebase.uri"));
        endpoint = "administrator-gateway/administrator-auth/api/administrator/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employeeauthrequest");
        bPost = true;
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        // Assert.assertEquals(200, clientPost.getResponseCode());

        // clearTestData();

        /**** Creation of the Admin / GP user *****/
        endpoint = "administrator-gateway/administrator-auth/api/administrator/register";
        jsonObject = processFileResouce.getNamedJSONObject("userRegistrationTestData");
        jsonObject.put("username", email);
        bPost = false;
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        // Assert.assertEquals(201, clientPost.getResponseCode());

        /**** Change pasword *****/
//		changePassword("admin@admin.com","m4sterpassw0rd?", "password");
    }

    //TODO need to refactor such that this method is removed and the above method is parameterised
    public static void createNhsBsaAdminUser(String emailId) throws IOException, JSONException, SQLException {

        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("microservicebase.uri"));
        endpoint = "administrator-gateway/administrator-auth/api/administrator/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employeeauthrequest");
        bPost = true;
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        // Assert.assertEquals(200, clientPost.getResponseCode());

        // clearTestData();

        /**** Creation of the Admin / GP user *****/
        endpoint = "administrator-gateway/administrator-auth/api/administrator/register";
        jsonObject = processFileResouce.getNamedJSONObject("NhsBsaAdminUserRegistrationTestData");
        jsonObject.put("username", emailId);
        bPost = false;
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        // Assert.assertEquals(201, clientPost.getResponseCode());

        changePassword(emailId, "password", "password");

    }

    public static void clearAccreditedLogoData() throws IOException {
        data = loadDatabaseDetails();

        /***** clearing down Accredited logo data *****/
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Statement stmt = conn.createStatement();
            Sql sql = new Sql(conn);

            System.out.println("Clearing down Accredited logo data");

            ResultSet rs = stmt
                    .executeQuery("SELECT user_id FROM employer_to_employer_user WHERE employer_id = '" + employerId + "'");

            while (rs.next()) {
                sql.select("DELETE FROM accredited_logo WHERE employer_id= '" + employerId + "'");
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }

    public static void clearVacancyAndJobAdvertdata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect vacancyConnect = new Connect();
            vacancyConnect.setConnection(data.getVacancyDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection vacancyConnection = vacancyConnect.getConnection();
            Statement stmt = vacancyConnection.createStatement();
            Sql vacancySql = new Sql(vacancyConnection);
            /****
             * First we need Job Reference No. With that Job Reference number need to get
             * id, employer_details_id, job_advert_id, vacancy_contact_id form Vacancy table
             * Then the deletion table order is vacancy_skill supporting_documents vacancy
             * vacancy_details vacancy_employer job_advert vacancy_contact
             *
             */
            System.out.println("Clearing down vacancy data ...");

            ResultSet rs = stmt.executeQuery(
                    "SELECT id, employer_details_id, job_advert_id, vacancy_contact_id, vacancy_details_id, job_reference,requirements_id,shortlisting_panel_id,approval_id "
                            + " FROM vacancy WHERE employer_code = '" + employerCode + "'");
            while (rs.next()) {
                vacancyID.add(rs.getInt("id"));
                vacancySql.select("DELETE FROM ui_data WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM skill_details_translation WHERE id in (select id from vacancy_skill WHERE vacancy_id = " + rs.getInt("id") + ");");
                vacancySql.select("DELETE FROM vacancy_skill WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM supporting_document WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM additional_locations WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy_advert_integration WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM pre_application_parameter_translation WHERE id in (select id from pre_application_question WHERE vacancy_id = " + rs.getInt("id") + ");");
                vacancySql.select("DELETE FROM pre_application_question WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM category_questions WHERE job_specific_category_id in ( select id from job_specific_categories where vacancy_id = " + rs.getInt("id") + ");");
                vacancySql.select("DELETE FROM job_specific_categories WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy_translation WHERE id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy_to_library_supporting_link WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM supporting_link WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy WHERE job_reference = '" + rs.getString("job_reference") + "';");
                vacancySql.select("DELETE FROM vacancy_details WHERE id = " + rs.getInt("vacancy_details_id") + ";");
                vacancySql.select("DELETE FROM vacancy_employer WHERE id = " + rs.getInt("employer_details_id") + ";");
                vacancySql.select("DELETE FROM job_advert WHERE id = " + rs.getInt("job_advert_id") + ";");
                vacancySql.select("DELETE FROM vacancy_contact WHERE id = " + rs.getInt("vacancy_contact_id") + ";");
                vacancySql
                        .select("DELETE FROM applicant_requirements WHERE id = " + rs.getInt("requirements_id") + ";");
                vacancySql.select("DELETE FROM shortlisting_panelists WHERE shortlisting_panel_id = " + rs.getInt("shortlisting_panel_id") + ";");
                vacancySql.select("DELETE FROM shortlisting_panel WHERE id = " + rs.getInt("shortlisting_panel_id") + ";");
                vacancySql.select("DELETE FROM approvers WHERE approval_id = " + rs.getInt("approval_id") + ";");
                vacancySql.select("DELETE FROM approval WHERE id = " + rs.getInt("approval_id") + ";");
                vacancySql.select("DELETE FROM workflow_task_audit WHERE vacancy_id = " + rs.getInt("id") + ";");
                job_reference.add(rs.getString("job_reference"));
            }
            vacancyConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down vacancy data with error :" + e.getMessage());
        }
        /***** clearing job advert data *****/
        try {
            Connect jobAdvertConnect = new Connect();
            jobAdvertConnect.setConnection(data.getJobadvertDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection jobadvertConnection = jobAdvertConnect.getConnection();
            Statement stmt2 = jobadvertConnection.createStatement();
            Sql jobAdvertSql = new Sql(jobadvertConnection);
            /****
             * First we need Job Reference No. With that Job Reference number need to get id
             * form job_advert table Then the deletion table order is job_advert_skill
             * supporting_document job_advert
             */
            System.out.println("Clearing down job advert data");

            for (int i = 0; i < job_reference.size(); i++) {
                ResultSet rs = stmt2
                        .executeQuery("SELECT id  FROM job_advert WHERE job_reference in ('" + job_reference.get(i) + "');");

                while (rs.next()) {
                    jobAdvertSql.select("DELETE FROM skill_details_translation WHERE id in (select id from job_advert_skill WHERE job_advert_id = " + rs.getInt("id") + ");");
                    jobAdvertSql.select("DELETE FROM job_advert_skill WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM supporting_document WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM additional_locations WHERE jobadvert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM pre_application_answer WHERE pre_application_question_id in (select id from pre_application_question where job_advert_id = " + rs.getInt("id") + ");");
                    jobAdvertSql.select("DELETE FROM pre_application_question WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM vacancy_translations WHERE id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM supporting_link WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM job_advert WHERE job_reference in ('" + job_reference.get(i) + "');");

                }
            }
            jobadvertConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down job advert data with error :" + e.getMessage());
        }
    }

    public static void clearOfferdata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect offerConnect = new Connect();
            offerConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection offerConnection = offerConnect.getConnection();
            Statement stmt = offerConnection.createStatement();
            Sql offerSql = new Sql(offerConnection);
            /****
             * First we need Vacancy ID With that Vacancy ID need to get
             * id from offer table
             * Then the deletion table order is offer_contact, offer_details, referee_details
             * ui_data, offer
             ****/
            System.out.println("Clearing down Offer data");
            ResultSet rs = stmt.executeQuery(
                    "SELECT id FROM offer WHERE organisation_name = '" + AdminUITestData.DEFAULT_ORGANISATION_NAME + "';");
            while (rs.next()) {
                offerSql.select("DELETE FROM offer_contact WHERE offer_id = " + rs.getInt("id") + ";");
                offerSql.select("DELETE FROM offer_details WHERE offer_id = " + rs.getInt("id") + ";");
                offerSql.select("DELETE FROM referee_details_audit WHERE referee_details_id in (select id from referee_details where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM referee_details WHERE offer_id in (" + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM ui_data WHERE offer_id = " + rs.getInt("id") + ";");
                offerSql.select("DELETE FROM offer_to_supporting_document WHERE offer_id in (" + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM offer_letter WHERE offer_id in (" + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM offer_term WHERE offer_id in (" + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM offer WHERE id = " + rs.getInt("id") + ";");
            }
            for (int ref_id : reference_id) {
                offerSql.select("DELETE FROM reference where id = " + ref_id + ";");
            }
            offerConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down offer data with error :" + e.getMessage());
        }
    }

    public static void clearOfferPreEmploymentdata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect offerConnect = new Connect();
            offerConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection offerConnection = offerConnect.getConnection();
            Statement stmt = offerConnection.createStatement();
            Sql offerSql = new Sql(offerConnection);
            /****
             * Clearing down pre employment data
             ****/
            System.out.println("Clearing down Offer Pre Employment data");
            ResultSet rs = stmt.executeQuery(
                    "SELECT id FROM offer WHERE organisation_name = '" + AdminUITestData.DEFAULT_ORGANISATION_NAME + "';");
            while (rs.next()) {
                offerSql.select("DELETE FROM preemployment_home_address_audit WHERE preemployment_home_address_id in (select id from preemployment_home_address where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_home_address WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_rtw_id in (select id from preemployment_rtw where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_rtw WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_qualifications_id in (select id from preemployment_qualifications where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_qualifications WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_dbs_id in (select id from preemployment_dbs where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_dbs WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_identification_id in (select id from preemployment_identification where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_identification WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_oh_id in (select id from preemployment_oh where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_oh WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_hpan_id in (select id from preemployment_hpan where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_hpan WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM notes WHERE preemployment_professionalreg_id in (select id from preemployment_professionalreg where preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + "));");
                offerSql.select("DELETE FROM preemployment_professionalreg WHERE preemployment_checks_id in (select id from preemployment_checks where offer_id = " + rs.getInt("id") + ");");
                offerSql.select("DELETE FROM preemployment_checks WHERE offer_id = " + rs.getInt("id") + ";");
            }
            offerConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down offer pre employment data with error :" + e.getMessage());
        }
    }

    /*public static void referenceIDRetrieve() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect offerConnect = new Connect();
            offerConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection offerConnection = offerConnect.getConnection();
            Statement stmt = offerConnection.createStatement();
            *//****
     * First we need Vacancy ID With that Vacancy ID need to get
     * id from offer table
     * Then the deletion table order is offer_contact, offer_details, referee_details
     * ui_data, offer
     ****//*
            ResultSet rs = stmt.executeQuery("select reference_id FROM referee_details where offer_id in (select id from offer where organisation_name = '" + AdminUITestData.DEFAULT_ORGANISATION_NAME + "');");
            while (rs.next()) {
                reference_id.add(rs.getInt("reference_id"));
            }
        } catch (Exception e) {
            System.out.println("Unable to retrieve reference_id with error :" + e.getMessage());
        }
    }

    public static String getGUID() throws IOException, SQLException {
        data = loadDatabaseDetails();
        Connect offerConnect = new Connect();
        offerConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                data.getUrl());
        Connection offerConnection = offerConnect.getConnection();
        Statement stmt = offerConnection.createStatement();
        Sql offerSql = new Sql(offerConnection);
        */

    /****
     * First we need Offer ID from offer table
     * Then with that offer id we need to get the guid from referee_details
     ****//*
        int offerID=0;
        
        ResultSet rs = stmt.executeQuery(
                "SELECT id FROM offer WHERE job_reference = '" + VacancyCreationStepsUtil.getJobReference() + "';");
        
        while (rs.next()) 
        {
        	offerID = rs.getInt("id");
        }
        
        ResultSet rs1 = stmt.executeQuery(
                "SELECT guid FROM referee_details WHERE offer_id = '" + offerID + "';");
        while (rs1.next()) {
            guid = rs1.getString("guid");
        }
        
        
        return guid;
    }
*/
    public static void clearESRVacancyAndJobAdvertdata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        ArrayList<String> job_ref = new ArrayList<>();
        String empCode = AdminUITestData.MULTI_ORG_EMPLOYER_CODE;
        try {
            Connect vacancyConnect = new Connect();
            vacancyConnect.setConnection(data.getVacancyDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection vacancyConnection = vacancyConnect.getConnection();
            Statement stmt = vacancyConnection.createStatement();
            Sql vacancySql = new Sql(vacancyConnection);
            /****
             * First we need Job Reference No. With that Job Reference number need to get
             * id, employer_details_id, job_advert_id, vacancy_contact_id form Vacancy table
             * Then the deletion table order is vacancy_skill supporting_documents vacancy
             * vacancy_details vacancy_employer job_advert vacancy_contact
             *
             */
            System.out.println("Clearing down ESR vacancy data");

            ResultSet rs = stmt.executeQuery(
                    "SELECT id, employer_details_id, job_advert_id, vacancy_contact_id, vacancy_details_id, job_reference,requirements_id "
                            + " FROM vacancy WHERE employer_code = '" + empCode + "'");

            while (rs.next()) {
                vacancySql.select("DELETE FROM ui_data WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy_skill WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM supporting_document WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM additional_locations WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy_advert_integration WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM pre_application_question WHERE vacancy_id = " + rs.getInt("id") + ";");
                vacancySql.select("DELETE FROM vacancy WHERE job_reference = '" + rs.getString("job_reference") + "';");
                vacancySql.select("DELETE FROM vacancy_details WHERE id = " + rs.getInt("vacancy_details_id") + ";");
                vacancySql.select("DELETE FROM vacancy_employer WHERE id = " + rs.getInt("employer_details_id") + ";");
                vacancySql.select("DELETE FROM job_advert WHERE id = " + rs.getInt("job_advert_id") + ";");
                vacancySql.select("DELETE FROM vacancy_contact WHERE id = " + rs.getInt("vacancy_contact_id") + ";");
                vacancySql
                        .select("DELETE FROM applicant_requirements WHERE id = " + rs.getInt("requirements_id") + ";");
                job_ref.add(rs.getString("job_reference"));
            }

            vacancyConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down ESR vacancy data with error :" + e.getMessage());
        }
        /***** clearing job advert data *****/
        try {
            Connect jobAdvertConnect = new Connect();
            jobAdvertConnect.setConnection(data.getJobadvertDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection jobadvertConnection = jobAdvertConnect.getConnection();
            Statement stmt2 = jobadvertConnection.createStatement();
            Sql jobAdvertSql = new Sql(jobadvertConnection);
            /****
             * First we need Job Reference No. With that Job Reference number need to get id
             * form job_advert table Then the deletion table order is job_advert_skill
             * supporting_document job_advert
             */
            System.out.println("Clearing down ESR job advert data");

            for (int i = 0; i < job_ref.size(); i++) {
                ResultSet rs = stmt2
                        .executeQuery("SELECT id  FROM job_advert WHERE job_reference in ('" + job_ref.get(i) + "');");

                while (rs.next()) {
                    jobAdvertSql.select("DELETE FROM job_advert_skill WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM supporting_document WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM additional_locations WHERE jobadvert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM pre_application_answer WHERE pre_application_question_id in (select id from pre_application_question where job_advert_id = " + rs.getInt("id") + ");");
                    jobAdvertSql.select("DELETE FROM pre_application_question WHERE job_advert_id = " + rs.getInt("id") + ";");
                    jobAdvertSql.select("DELETE FROM job_advert WHERE job_reference in ('" + job_ref.get(i) + "');");

                }
            }
            jobadvertConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down ESR job advert data with error :" + e.getMessage());
        }
    }

    public static void unlockAccount() {
        /***** Unlocking administrator user *****/
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Statement stmt = conn.createStatement();
            Sql sql = new Sql(conn);

            System.out.println("Unlocking Employer user");

            sql.select("UPDATE employer_user set locked = null, unsuccessful_auth_attempts = null, locked_time = null where email_address = 'admin@admin.com';");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to unlock administrator user:" + e.getMessage());
        }
    }

    public static void clearContractTemplatedata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect issueContractConnect = new Connect();
            issueContractConnect.setConnection(data.getIssueContractDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection issueContractConnection = issueContractConnect.getConnection();
            Statement stmt = issueContractConnection.createStatement();
            Sql issueContractSql = new Sql(issueContractConnection);
            /****
             * Clearing down issue contract data
             ****/
            System.out.println("Clearing down Contract Template data");

            issueContractSql.select("DELETE FROM contract_template_term WHERE contract_template_id in (select id from contract_template where employer_id in (" + employerId + "));");
            issueContractSql.select("DELETE FROM contract_template where employer_id in (" + employerId + ");");

            issueContractConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down contract template data with error :" + e.getMessage());
        }
    }


    public static void clearOfferTemplatedata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect offerTemplateConnect = new Connect();
            offerTemplateConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection offerTemplateConnection = offerTemplateConnect.getConnection();
            Statement stmt = offerTemplateConnection.createStatement();
            Sql offertemplateSql = new Sql(offerTemplateConnection);
            /****
             * Clearing down offer template data
             ****/
            System.out.println("Clearing down offer Template data");

            offertemplateSql.select("DELETE FROM offer_template_term WHERE offer_template_id in (select id from offer_template where employer_id in (" + employerId + "));");
            offertemplateSql.select("DELETE FROM offer_template where employer_id in (" + employerId + ");");

            offerTemplateConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down offer template data with error :" + e.getMessage());
        }
    }

    public static void clearIssueContractdata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect issueContractConnect = new Connect();
            issueContractConnect.setConnection(data.getIssueContractDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection issueContractConnection = issueContractConnect.getConnection();
            Statement stmt = issueContractConnection.createStatement();
            Sql issueContractSql = new Sql(issueContractConnection);
            /****
             * Clearing down issue contract data
             ****/
            System.out.println("Clearing down Issue Contract data");

            issueContractSql.select("DELETE FROM contract_term WHERE contract_id in (select id from contract where employer_id in (" + employerId + "));");
            issueContractSql.select("DELETE FROM contract where employer_id in (" + employerId + ");");

            issueContractConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down issue contract data with error :" + e.getMessage());
        }
    }

    public static void clearOfferSupportingDocumentData() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect offerConnect = new Connect();
            offerConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection offerConnection = offerConnect.getConnection();
            Statement stmt = offerConnection.createStatement();
            Sql offerSql = new Sql(offerConnection);

            Connect issueContractConnect = new Connect();
            issueContractConnect.setConnection(data.getIssueContractDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection issueContractConnection = issueContractConnect.getConnection();
            Sql issueContractSql = new Sql(issueContractConnection);

            /****
             * First we need supporting document ID
             * id from offer table
             * Then the deletion table order is offer_to_supporting_document, contract_to_supporting_document
             ****/
            System.out.println("Clearing down Offer & Contract Supporting Documents data");

            ResultSet rs = stmt
                    .executeQuery("SELECT id FROM supporting_document WHERE employer_id in ('" + employerId + "');");
            while (rs.next()) {
                offerSql.select("DELETE FROM offer_to_supporting_document WHERE supporting_document_id in (" + rs.getInt("id") + ");");
                issueContractSql.select("DELETE FROM contract_to_supporting_document WHERE supporting_document_id in (" + rs.getInt("id") + ");");
                //TODO: here I may need to delete advert and contract data as well
            }

            offerSql.select("DELETE FROM supporting_document WHERE employer_id in (" + employerId + ");");

            offerConnection.close();


        } catch (Exception e) {
            System.out.println("Unable to clear down offer and contract supporting document data with error :" + e.getMessage());
        }

    }

    public static void clearOfferSupportingLinkData() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect offerConnect = new Connect();
            offerConnect.setConnection(data.getOfferDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection offerConnection = offerConnect.getConnection();
            Statement stmt = offerConnection.createStatement();
            Sql offerSql = new Sql(offerConnection);

            Connect issueContractConnect = new Connect();
            issueContractConnect.setConnection(data.getIssueContractDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection issueContractConnection = issueContractConnect.getConnection();
            Sql issueContractSql = new Sql(issueContractConnection);

            /****
             * First we need supporting document ID
             * id from offer table
             * Then the deletion table order is offer_to_supporting_link, contract_to_supporting_link
             ****/
            System.out.println("Clearing down Offer and Contract Supporting links data");

            ResultSet rs = stmt
                    .executeQuery("SELECT id FROM supporting_link WHERE employer_id in ('" + employerId + "');");
            while (rs.next()) {
                offerSql.select("DELETE FROM offer_to_supporting_link WHERE supporting_link_id in (" + rs.getInt("id") + ");");
                issueContractSql.select("DELETE FROM contract_to_supporting_link WHERE supporting_link_id in (" + rs.getInt("id") + ");");

                //TODO: here I may need to delete advert and contract data as well
            }

            offerSql.select("DELETE FROM supporting_link WHERE employer_id in (" + employerId + ");");

            offerConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down offer and contract supporting links data with error :" + e.getMessage());
        }
    }

    /**** New user creation flow after password automation changes *****/
    public static void createUsersWithSpecificRole(String role, String firstName, String lastName, String jobTitle, String emailAddress, String password) throws IOException, JSONException, SQLException {
        /**** Authentication of the NHSBSA user for token to create new users *****/
        processFileResouce = new ProcessFileResouce("jsonFiles/storydatafile.json");
        GlobalVarsHelper.getInstance().setURL(properties.getProperty("microservicebase.uri"));
        endpoint = "administrator-gateway/administrator-auth/api/administrator/auth";
        jsonObject = processFileResouce.getNamedJSONObject("employeeauthrequest");
        bPost = true;
        /**** Set Auth code as false when authenticating NHSBSA user *****/
        GlobalVarsHelper.getInstance().setUseAuthcode(false);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);
        // Assert.assertEquals(200, clientPost.getResponseCode());
        if (clientPost.getResponseCode() == 200) {
            System.out.println("Authenticated NHSBSA ");
            Assert.assertEquals(200, clientPost.getResponseCode());
        } else {
            System.out.println("Unable to Authenticate NHSBSA " + clientPost.getResponseCode());
            Assert.assertTrue(false);
        }

        /**** Creation of the Recruitment Manager & Recruitment Administrator user *****/
        jsonObject = processFileResouce.getNamedJSONObject("employerRegistrationTestData");
        employerCode = jsonObject.getString("employerCode");
        employerId = getEmployerID(employerCode);
        endpoint = "administrator-gateway/administrator-auth/api/administrator/register/user";
        JSONObject jsonObject = new JSONObject(
                "{\n" + "\"roles\": [" + role + "],\n" + " \"firstName\": " + firstName + ",\n" + " \"lastName\": " + lastName + ",\n"
                        + " \"jobTitle\": " + jobTitle + ",\n " + " \"emailAddress\": " + emailAddress + ",\n "
                        + " \"password\": " + password + ",\n " + " \"employerId\": " + employerId + " \n" + "}");
        bPost = false;
        /**** Set Auth code as true when creating the new user *****/
        GlobalVarsHelper.getInstance().setUseAuthcode(true);
        clientPost = new HTTPClientPost(GlobalVarsHelper.getInstance().getURL() + endpoint, jsonObject);

        if (clientPost.getResponseCode() == 201) {
            System.out.println("Created User: ");
            Assert.assertEquals(201, clientPost.getResponseCode());
        } else {
            String response = clientPost.getContent();

            JSONObject jsonObj = new JSONObject(response);

            JSONArray arr = jsonObj.getJSONArray("errors");

            System.out.println("Unable to Create User: " + arr.getJSONObject(0).getString("description"));

            Assert.assertTrue(false);
        }
    }

    public static void clearWorkflowdata() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect workflowConnect = new Connect();
            workflowConnect.setConnection(data.getWorkflowDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection workflowConnection = workflowConnect.getConnection();
            Statement stmt = workflowConnection.createStatement();
            Sql workflowSql = new Sql(workflowConnection);
            /****
             * Clearing down Workflow data
             ****/
            System.out.println("Clearing down Workflow data");

            if (vacancyID.size() > 0) {
                for (int vacancy : vacancyID) {
                    ResultSet rs = stmt.executeQuery(
                            "SELECT id FROM vacancy WHERE vacancy_id = '" + vacancy + "';");
                    while (rs.next()) {
                        workflowSql.select("DELETE FROM task WHERE vacancy_id = " + rs.getInt("id") + ";");
                        workflowSql.select("DELETE FROM assignment WHERE vacancy_id = " + rs.getInt("id") + ";");
                        workflowSql.select("DELETE FROM vacancy WHERE vacancy_id = " + vacancy + ";");
                    }
                }
            }
            workflowSql.select("DELETE FROM employer_kpi WHERE employer_id in (" + employerId + ");");
            workflowConnection.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down workflow data with error :" + e.getMessage());
        }
    }

    public static void clearOverviewOfYourOrganisationTemplateData() throws IOException, SQLException {
        data = loadDatabaseDetails();
        try {
            Connect orgOverviewTemplateConnect = new Connect();
            orgOverviewTemplateConnect.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection orgOverviewTemplateConnection = orgOverviewTemplateConnect.getConnection();
            Sql orgOverviewTemplateSql = new Sql(orgOverviewTemplateConnection);

            System.out.println("Clearing down overview of your organisation template data");
            orgOverviewTemplateSql.select("DELETE FROM overview WHERE employer_id= '" + employerId + "'");
            orgOverviewTemplateConnection.close();
        } catch (Exception e) {
            System.out.println(
                    "Unable to clear down overview of your organisation template data with error :" + e.getMessage());
        }
    }

    /*public static String getApplicationRef() throws IOException, SQLException {
        data = loadDatabaseDetails();
        String application_ref = null;
        try {
            Connect applicationConnect = new Connect();
            applicationConnect.setConnection(data.getApplicationDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection applicationConnection = applicationConnect.getConnection();
            Statement stmt2 = applicationConnection.createStatement();

            String jobReferenceNumber = VacancyCreationStepsUtil.getJobReference();

            ResultSet rs = stmt2
                    .executeQuery("SELECT application_reference  FROM application WHERE job_reference = '" + jobReferenceNumber + "' AND application_state = 'PRE_EMPLOYMENT_CHECKS';");
            while (rs.next()) {
                application_ref = rs.getString("application_reference");
            }
            applicationConnection.close();
        } catch (
                Exception e) {
            System.out.println("Unable to get application reference with error: " + e.getMessage());
        }
        return application_ref;
    }
*/
    public static String getVacancyReference(String vacancyStatus) throws IOException, SQLException {
        data = loadDatabaseDetails();
        String job_reference = null;
        try {
            Connect vacancyConnect = new Connect();
            vacancyConnect.setConnection(data.getVacancyDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection vacancyConnection = vacancyConnect.getConnection();
            Statement stmt = vacancyConnection.createStatement();
            Sql vacancySql = new Sql(vacancyConnection);

            ResultSet rs = stmt
                    .executeQuery("SELECT job_reference FROM vacancy WHERE id = (select max(id) from vacancy where publish_state = '" + vacancyStatus + "');");
            while (rs.next()) {
                job_reference = rs.getString("job_reference");
            }
            vacancyConnection.close();
        } catch (
                Exception e) {
            System.out.println("Unable to get DRAFT Job reference number with error: " + e.getMessage());
        }
        return job_reference;
    }

    /*public static void changeSafeguardingSettingToYes() throws Throwable
    {

        TestLoginSteps testLoginSteps = new TestLoginSteps();
        testLoginSteps.testEmployerStart();
        testLoginSteps.loginIntoEmployerPortal();

        DashboardPageSteps dashboardPageSteps = new DashboardPageSteps();
        dashboardPageSteps.i_change_the_Safeguarding_settings_to_yes_add__to_all_job_applications();

        WebDrv.getInstance().getWebDriver().quit();
        WebDrv.setInstance();

    }*/
}
