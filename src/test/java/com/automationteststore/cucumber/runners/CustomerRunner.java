package com.automationteststore.cucumber.runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

@CucumberOptions(
        monochrome = true,
        features = {"src/test/java/com/automationteststore/cucumber/features"},
        glue = {"com.automationteststore.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-html", "json:target/cucumber.json"},
        tags = "@CandidateRegressionNew")


public class CustomerRunner {

    @BeforeClass
    public static void setup() throws Throwable {
/*		UIClearDownHelper.clearEmployerUserAndEmployerTestData(AdminUITestData.MULTI_ORG_EMPLOYER_CODE);
		SetUpHelper.clearTestData();
		SetUpHelper.changePassword("nhsbsa.nhsjobs@nhsbsa.nhs.uk", "m4sterpassw0rd?", "Password1234");
		SetUpHelper.createUser();
		CandidateSetUpHelper.changeSafeguardingSetting();
		CandidateSetUpHelper.LoginAsEmployerUser();*/
    }

    @AfterClass
    public static void tearDown() throws IOException, InterruptedException {
/*		SetUpHelper.clearCandidateTestData();
		SetUpHelper.clearTestData();*/
    }
}
