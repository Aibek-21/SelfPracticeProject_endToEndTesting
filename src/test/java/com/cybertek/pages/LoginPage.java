package com.cybertek.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cybertek.utilities.Driver;


public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    @FindBy(id = "inputEmail")
    public WebElement emailAddressBox;
    @FindBy(id = "inputPassword")
    public WebElement passwordBox;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement signButton;




}
