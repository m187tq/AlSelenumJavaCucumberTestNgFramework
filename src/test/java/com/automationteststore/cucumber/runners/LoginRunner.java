package com.automationteststore.cucumber.runners;

import com.automationteststore.webdriverutilities.WebDrv;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        dryRun = false,
        //name = {"Login"},
        monochrome = true,
        features = {"src/test/java/com/automationteststore/cucumber/features"},
        glue = {"com.automationteststore.cucumber.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "html:cucumberReport/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        tags = "@Login"
        //tags = "@LoginMap"

)

public class LoginRunner extends AbstractTestNGCucumberTests {
/*  @Override
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		return super.scenarios();
	}*/


}
