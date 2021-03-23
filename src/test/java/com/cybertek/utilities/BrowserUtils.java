package com.cybertek.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrowserUtils {


    public static List<String> getElementsText(List<WebElement> webElementList) {

        //Placeholder empty list of web elements
        List<String> webElementsAsString = new ArrayList<>();
        //looping through list of web elements and storing text into placeholder list
        for (WebElement each : webElementList) {
            webElementsAsString.add(each.getText());
        }
        //returning final List<String>
        return webElementsAsString;
    }
    //Method to assert title

    public static void titleVerification(String expectedTitle) {
        String actualTitle = Driver.getDriver().getTitle();
        Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedTitle));

    }

    //create method name: wait
    //converting milliseconds to seconds
    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {
            System.out.println("something happened in sleep method");

        }
    }

    public static void clickToElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();

    }


    public static class ConfigurationReader {

    //in this class we implement repeated steps of reading
        //from configuration.properties file

        //1-create object of properties
        private static Properties properties = new Properties();

        static {
            //2-get the path and open the file
            try {
                FileInputStream file = new FileInputStream("configuration.properties");
                //3-load the opened file into properties object
                properties.load(file);

                //closing the file in JVM memory
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //4-use the object to read from the conf.properties
        public static String getProperty(String keyWord) {

            return properties.getProperty(keyWord);

        }



    }
}