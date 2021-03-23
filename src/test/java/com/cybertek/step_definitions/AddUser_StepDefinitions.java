package com.cybertek.step_definitions;

import com.cybertek.pages.LandingPage;
import com.cybertek.utilities.BrowserUtils;
import com.cybertek.utilities.DB_Utility;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.cybertek.pages.LoginPage;
import com.cybertek.utilities.Driver;
import com.cybertek.utilities.ConfigurationReader;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.*;

import java.sql.Connection;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddUser_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();


    @Given("the librarian is on the users page")
    public void the_librarian_is_on_the_users_page() {
        BrowserUtils.sleep(5);
        String emailAddress = ConfigurationReader.getProperty("librarianUsername");
        String password = ConfigurationReader.getProperty("librarianPassword");
        loginPage.emailAddressBox.sendKeys(emailAddress);
        loginPage.passwordBox.sendKeys(password);
        loginPage.signButton.click();
        landingPage.usersModule.click();
    }

    @When("user  click add user")
    public void user_click_add_user() {
        landingPage.addUserLink.click();
    }

    @When("user enter full Name {string}")
    public void user_enter_full_name(String string) {
        landingPage.fullNameBox.sendKeys(string);
    }

    @When("user enter password {string}")
    public void user_enter_password(String string) {
        landingPage.passwordBox.sendKeys(string);

    }

    @When("user enter user Group {string}")
    public void user_enter_user_group(String string) {
        Select select = new Select(landingPage.userGroupBox);
        select.selectByVisibleText(string);
    }

    @When("user enter start Date {string}")
    public void user_enter_start_date(String string) {
        landingPage.startDateBox.sendKeys(string + Keys.ENTER);
    }

    @When("user enter email {string}")
    public void user_enter_email(String string) {
        landingPage.emailBox.sendKeys(string);
    }

    @When("user enter status {string}")
    public void user_enter_status(String string) {
        Select select = new Select(landingPage.statusBox);
        select.selectByVisibleText(string);
    }

    @When("user enter end date {string}")
    public void user_enter_end_date(String string) {
        landingPage.endDateBox.sendKeys(string + Keys.ENTER);
    }

    @And("user enter address {string}")
    public void userEnterAddress(String string) {
        landingPage.addressBox.sendKeys(string);
    }

    @Then("user should be displayed by {string}")
    public void user_should_be_displayed_by(String string) {
        landingPage.saveChangesButton.click();
        System.out.println(landingPage.cellValue(string));
        //  Wait wait=new WebDriverWait(Driver.getDriver(), 10);
        //  wait.until((ExpectedConditions.visibilityOf(landingPage.cellValue(string))));
        Wait wait = new FluentWait(Driver.getDriver()).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(landingPage.cellValue(string)));
        BrowserUtils.sleep(5);
        Assert.assertEquals(string, landingPage.cellValue(string).getText());

    }


    @And("user should verify fullName with database {string}")
    public void userShouldVerifyFullNameWithDatabase(String string) {
        String url = ConfigurationReader.getProperty("dbURL");
        String username = ConfigurationReader.getProperty("dbUN");
        String password = ConfigurationReader.getProperty("dbPWD");
        DB_Utility.createConnection(url, username, password);
        String query = "select lower( full_name )\n" +
                "from users where full_name like '" + string + "'";
        DB_Utility.runQuery(query);
        String expectedREsult = DB_Utility.getFirstRowFirstColumn();
        Assert.assertEquals(string, expectedREsult);

    }
}
