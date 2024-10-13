package com.automationteststore.cucumber.stepDefinitions;

import com.automationteststore.cucumber.runners.SetUpHelper;
import com.automationteststore.helperutilities.UIClearDownHelper;
import com.automationteststore.helperutilities.FileUtils;
import com.automationteststore.testData.admin.AdminTestData;
import com.automationteststore.testData.admin.AdminUITestData;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.io.IOException;
import java.sql.SQLException;


public class Hooks {

    @After("@DeleteDepartment")
    public void exitDeleteDepartment() throws IOException, SQLException {
        UIClearDownHelper.deleteDepartment(AdminUITestData.DEFAULT_EMPLOYER_CODE);
    }

    @After("@DeleteEmployer")
    public void exitDeleteEmployer() throws IOException, SQLException {
        UIClearDownHelper.deleteEmployer(AdminUITestData.EMPLOYER_CODE);
    }

    @After("@DeleteEmployerUser")
    public void exitDeleteEmployerUser() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser(AdminUITestData.USER_EMAIL_ADDRESS);
    }

    @After("@DeleteEmployerUser2")
    public void exitDeleteEmployerUser2() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail@address.com");
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
    }

    @After("@Delete3EmployerUsers")
    public void exitDelete3EmployerUsers() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail@address.com");
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
        UIClearDownHelper.clearEmployerUser("useremail3@address.com");
    }

    @After("@DeleteEmployerUser3")
    public void exitDeleteEmployer3Users() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail@address.com");
        UIClearDownHelper.clearEmployerUser("useremail1@address.com");
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
    }

    @After("@DeleteEmployerUser6")
    public void exitDeleteEmployerUser6() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail1@address.com");
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
        UIClearDownHelper.clearEmployerUser("useremail3@address.com");
        UIClearDownHelper.clearEmployerUser("useremail4@address.com");
        UIClearDownHelper.clearEmployerUser("useremail5@address.com");
        UIClearDownHelper.clearEmployerUser("useremail6@address.com");
    }

    @After("@DeleteEmployerUser10")
    public void exitDeleteEmployerUser10() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
        UIClearDownHelper.clearEmployerUser("useremail3@address.com");
        UIClearDownHelper.clearEmployerUser("useremail4@address.com");
        UIClearDownHelper.clearEmployerUser("useremail5@address.com");
        UIClearDownHelper.clearEmployerUser("useremail6@address.com");
        UIClearDownHelper.clearEmployerUser("useremail7@address.com");
        UIClearDownHelper.clearEmployerUser("useremail8@address.com");
        UIClearDownHelper.clearEmployerUser("useremail9@address.com");
        UIClearDownHelper.clearEmployerUser("useremail10@address.com");
        UIClearDownHelper.clearEmployerUser("useremail11@address.com");
    }

    @After("@DeleteEmployerUser15")
    public void exitDeleteEmployerUser15() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail@address.com");
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
        UIClearDownHelper.clearEmployerUser("useremail3@address.com");
        UIClearDownHelper.clearEmployerUser("useremail4@address.com");
        UIClearDownHelper.clearEmployerUser("useremail5@address.com");
        UIClearDownHelper.clearEmployerUser("useremail6@address.com");
        UIClearDownHelper.clearEmployerUser("useremail7@address.com");
        UIClearDownHelper.clearEmployerUser("useremail8@address.com");
        UIClearDownHelper.clearEmployerUser("useremail9@address.com");
        UIClearDownHelper.clearEmployerUser("useremail10@address.com");
        UIClearDownHelper.clearEmployerUser("useremail11@address.com");
        UIClearDownHelper.clearEmployerUser("useremail12@address.com");
        UIClearDownHelper.clearEmployerUser("useremail13@address.com");
        UIClearDownHelper.clearEmployerUser("useremail14@address.com");
        UIClearDownHelper.clearEmployerUser("useremail15@address.com");
    }

    @After("@DeleteEmployerUser2")
    public void exitDeleteEmployerUser3() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser("useremail2@address.com");
        UIClearDownHelper.clearEmployerUser("useremail3@address.com");
    }

    @After("@DeleteAdminEmployerUser")
    public void exitDeleteAdminEmployerUser() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser(AdminTestData.EMP_EMAIL);
    }

    @Before("@DeleteEmployerUser")
    public void beforeDeleteEmployerUser() throws IOException, SQLException {
        UIClearDownHelper.clearEmployerUser(AdminUITestData.USER_EMAIL_ADDRESS);
    }

    @After("@DeleteEmployerAndEmployerUser")
    public void exitDeleteEmployerandEmployerUser() throws Throwable {
        UIClearDownHelper.clearEmployerUserAndEmployerTestData(AdminUITestData.MULTI_ORG_EMPLOYER_CODE);
    }

    @After("@DeleteAllEmployerAndEmployerUser")
    public void exitDeleteAllEmployerandEmployerUser() throws Throwable {
        UIClearDownHelper.clearAllEmployerUserAndEmployerTestData(AdminUITestData.MULTI_ORG_EMPLOYER_CODE);
    }

    /********  Used in Manage At Risk user Features   ***********/

    @After("@DeleteAtRiskUser")
    public void exitDeleteAtRiskUser() throws IOException, SQLException {
        System.out.println("In After tag for at risk....");
        UIClearDownHelper.deleteAtRiskUser();
    }

    @After("@DeleteAtRiskUserFromCandidateAuth")
    public void exitDeleteAtRiskUserFromCandidateAuth() throws IOException, SQLException {
        System.out.println("In After tag for this....");
        UIClearDownHelper.deleteAtRiskUserFromCandidateAuth(AdminTestData.USER_EMAIL_ADDRESS);
    }

    /********************  Used for clearing Test data ***********************/


    @After("@clearVacancyAndJobAdvertData")
    public void clearVacancyAndJobAdvertData() throws IOException, SQLException {
        SetUpHelper.clearVacancyAndJobAdvertdata();
    }

    @After("@clearPreEmpData")
    public void clearOfferPreEmploymentData() throws IOException, SQLException {
        SetUpHelper.clearOfferPreEmploymentdata();
    }

    @After("@unlock")
    public void unlockAccount() {
        SetUpHelper.unlockAccount();
    }

    @After(value = "@clearContractTemplateData", order = 2)
    public void clearContractTemplatedata() throws IOException, SQLException {
        SetUpHelper.clearContractTemplatedata();
    }

    @After("@clearOfferTemplateData")
    public void clearOfferTemplatedata() throws IOException, SQLException {
        SetUpHelper.clearOfferTemplatedata();
    }

    @After(value = "@clearSupportingDocumentData", order = 4)
    public void clearSupportingDocumentData() throws IOException, SQLException {
        SetUpHelper.clearOfferSupportingDocumentData();
    }

    @After(value = "@clearSupportingLinkData", order = 3)
    public void clearSupportingLinkData() throws IOException, SQLException {
        SetUpHelper.clearOfferSupportingLinkData();
    }

    @After(value = "@clearIssueContractData", order = 1)
    public void clearIssueContractData() throws IOException, SQLException {
        SetUpHelper.clearIssueContractdata();
    }

    @After("@clearOrgOverviewTemplateData")
    public void clearOverviewOfYourOrganisationTemplateData() throws IOException, SQLException {
        SetUpHelper.clearOverviewOfYourOrganisationTemplateData();
    }


    /********************  Used for All Welsh Vacancy related Scenarios ***********************/

    @Before("@deleteReportingDownloadedReports")
    public void deleteReportingFiles() {
        FileUtils.deleteReportingFiles();
    }

    @After("@deleteDownloadedCSV")
    public void deleteCSVFiles() {
        FileUtils.deleteCSVFiles();
    }

}
