package com.cybertek.step_definitions;

import com.cybertek.pages.LoginPage;
import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class Hooks {

   // LoginPage loginPage=new LoginPage();

    @Before()
    public void setUpDriver() {
        Driver.getDriver().get(ConfigurationReader.getProperty("library2Url"));

    }

    @After()
    public void teardown() {
        Driver.closeDriver();
    }

}