package com.cybertek.pages;

import com.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    public LandingPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//span[.='Users']")
    public WebElement usersModule;

    @FindBy(xpath = "//a[@class='btn btn-lg btn-outline btn-primary btn-sm']")
    public WebElement addUserLink;

    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullNameBox;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordBox;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailBox;

    @FindBy(id = "user_group_id")
    public WebElement userGroupBox;

    @FindBy(id = "status")
    public WebElement statusBox;

    @FindBy(xpath = "//input[@name='start_date']")
    public WebElement startDateBox;

    @FindBy(xpath = "//input[@name='end_date']")
    public WebElement endDateBox;

    @FindBy(id = "address")
    public WebElement addressBox;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement saveChangesButton;


    public WebElement cellValue(String name) {

        return Driver.getDriver().findElement(By.xpath("//td[.='" + name + "']"));
    }
}
