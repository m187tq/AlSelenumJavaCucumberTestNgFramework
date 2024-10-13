package com.automationteststore.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        dryRun = false,
        monochrome = true,
        features = {"src/test/java/com/automationteststore/cucumber/features"},
        glue = {"com.automationteststore.cucumber.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "html:cucumberReport/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        //tags = "@Homepage_CompleteOrder_E2E_1",
        //tags = "@Homepage_CompleteOrder_E2E_2"
        tags = "@ShoppingCart"


)

public class CompleteOrderRunner extends AbstractTestNGCucumberTests {
/*  @Override
	@DataProvider(parallel=true)
	public Object[][] scenarios() {
		return super.scenarios();
	}*/

}
