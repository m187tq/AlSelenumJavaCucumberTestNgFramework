package com.automationteststore.helperutilities;

import com.automationteststore.cucumber.runners.SetUpHelper;
import com.automationteststore.helperutilities.database.Sql;
import com.automationteststore.helperutilities.date.DataSettings;
import com.automationteststore.testData.admin.AdminUITestData;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UIClearDownHelper {

    public static DataSettings data;

    public static void deleteEmployer(String employerCode) throws IOException, SQLException {
        data = SetUpHelper.loadDatabaseDetails();
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Sql sql = new Sql(conn);
            /******* Clearing User and Employer data ******/
            System.out.println("Clearing down administrator data");

            sql.select("DELETE FROM administrator WHERE employer_code ='" + employerCode + "';");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }

    public static void clearEmployerUser(String emailAddress) throws IOException, SQLException {
        data = SetUpHelper.loadDatabaseDetails();
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Sql sql = new Sql(conn);
            /******* Clearing User and Employer data ******/
            System.out.println("Clearing down Employer-user data");

            sql.select("DELETE FROM employer_to_employer_user WHERE user_id in (select id from employer_user where email_address = '" + emailAddress + "');");
            sql.select("DELETE FROM employer_user WHERE email_address = '" + emailAddress + "';");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }

    public static void deleteAtRiskUser() throws IOException, SQLException {
        data = SetUpHelper.loadDatabaseDetails();
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Sql sql = new Sql(conn);
            /******* Clearing User and Employer data ******/
            System.out.println("Clearing down Employer-user data");

            int tempEmployerId = SetUpHelper.getEmployerID(AdminUITestData.DEFAULT_EMPLOYER_CODE);

            sql.select("DELETE FROM atriskcandidates WHERE employer_id= '" + tempEmployerId + "'");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }

    public static void deleteAtRiskUserFromCandidateAuth(String username) throws IOException, SQLException {
        data = SetUpHelper.loadDatabaseDetails();

        /**** clearing down Candidate Auth data ****/
        System.out.println("clearing down customer auth data");
        try {
            Connect candidateAuthConnect = new Connect();
            candidateAuthConnect.setConnection(data.getCandidateDatabase(), data.getDatabaseusername(),
                    data.getDatabasepassword(), data.getUrl());
            Connection candidateAuthConn = candidateAuthConnect.getConnection();
            Statement stmt = candidateAuthConn.createStatement();
            Sql candSql = new Sql(candidateAuthConn);


            candSql.select("DELETE FROM candidate_user WHERE username = '" + username + "'");

            candidateAuthConn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down customer auth data with error :" + e.getMessage());
        }

    }

    public static void clearEmployerUserAndEmployerTestData(String employerCode) throws IOException {
        data = SetUpHelper.loadDatabaseDetails();

        /***** clearing down Employer-user and Employer data *****/
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Statement stmt = conn.createStatement();
            Sql sql = new Sql(conn);

            System.out.println("Clearing down Employer-user and Employer data");
            int tempEmployerId = SetUpHelper.getEmployerID(employerCode);

            ResultSet rs = stmt
                    .executeQuery("SELECT user_id FROM employer_to_employer_user WHERE employer_id = '" + tempEmployerId + "'");

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                sql.select("DELETE FROM employer_to_employer_user WHERE employer_id = " + tempEmployerId + "AND user_id ='" + userId + "';");
                sql.select("DELETE FROM atriskcandidates WHERE employer_id= '" + tempEmployerId + "'");
                sql.select("DELETE FROM accredited_logo WHERE employer_id= '" + tempEmployerId + "'");
            }


            sql.select("DELETE FROM administrator WHERE id = " + tempEmployerId + "AND employer_code ='" + employerCode + "';");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }

    public static void clearAllEmployerUserAndEmployerTestData(String employerCode) throws Throwable {
        data = SetUpHelper.loadDatabaseDetails();

        /***** clearing down Employer-user and Employer data *****/
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Statement stmt = conn.createStatement();
            Sql sql = new Sql(conn);

            System.out.println("Clearing down Employer-user and Employer data");
            int tempEmployerId = SetUpHelper.getEmployerID(employerCode);
            int tempUserId = SetUpHelper.getUserId(tempEmployerId);


            sql.select("DELETE FROM employer_to_employer_user WHERE user_id in ('" + tempUserId + "');");
            sql.select("DELETE FROM employer_user WHERE id =" + tempUserId + ";");
            sql.select("DELETE FROM atriskcandidates WHERE employer_id= '" + tempEmployerId + "'");
            sql.select("DELETE FROM accredited_logo WHERE employer_id= '" + tempEmployerId + "'");


            sql.select("DELETE FROM administrator WHERE id = " + tempEmployerId + "AND employer_code ='" + employerCode + "';");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }

    public static void deleteDepartment(String employerCode) throws IOException, SQLException {
        data = SetUpHelper.loadDatabaseDetails();
        try {
            Connect c = new Connect();
            c.setConnection(data.getEmployerDatabase(), data.getDatabaseusername(), data.getDatabasepassword(),
                    data.getUrl());
            Connection conn = c.getConnection();
            Statement stmt1 = conn.createStatement();
            Sql sql = new Sql(conn);

            int tempEmployerId = SetUpHelper.getEmployerID(employerCode);

            ResultSet rs = stmt1.executeQuery(
                    "SELECT id FROM department WHERE employer_id ='" + tempEmployerId + "'");

            while (rs.next()) {
                sql.select("DELETE FROM department_to_employer_user WHERE employer_id= '" + rs.getInt("id") + "'");

                //"WHERE employer_id= " needs to be changed, once the BE column name changed. 
            }

            sql.select("DELETE FROM department WHERE employer_id ='" + tempEmployerId + "'");

            conn.close();
        } catch (Exception e) {
            System.out.println("Unable to clear down administrator auth data with error :" + e.getMessage());
        }
    }


}