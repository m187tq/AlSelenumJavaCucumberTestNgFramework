package com.automationteststore.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        dryRun = false,
        monochrome = true,
        features = {"src/test/java/com/automationteststore/cucumber/features"},
        glue = {"com.automationteststore.cucumber.stepDefinitions"},
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "html:cucumberReport/cucumber.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        //tags = "@Reg",
        //tags = "@Login",
        //tags = "@Menu",
        //tags = "@CompleteOrder",
        //tags = "@Menu_CompleteOrder_E2E_1",
        //tags = "@Menu_CompleteOrder_E2E_2",
        //tags = "@Menu_CompleteOrder_E2E_3",
        //tags = "@RegistrationAndLogin",
        //tags = "@Registration001",
        //tags = "@RegisterDataTableMapSteps",
        //tags = "@RegisterAndLoginAndLogout"
        //tags = "@RegistrationDataTableMap"
        //tags = "@RegistrationDataTableTransformerShort"
        //tags = "@RegistrationDataTableTransformer",
        //tags = "@NavigationMenu"
        tags = "@CategoryMenuBar"


)

public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
