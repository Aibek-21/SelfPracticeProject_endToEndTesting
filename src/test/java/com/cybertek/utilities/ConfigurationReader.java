package com.cybertek.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

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
