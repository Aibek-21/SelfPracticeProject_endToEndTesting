package com.cybertek.test;

import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.DB_Utility;
import org.junit.Assert;
import org.junit.Test;
import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.DB_Utility;

public class SimpleTest {


    @Test
    public void addUsers() {

        String url = ConfigurationReader.getProperty("library2Url");
        String username = ConfigurationReader.getProperty("librarianUsername");
        String password = ConfigurationReader.getProperty("librarianPassword");

        DB_Utility.createConnection(url, username, password);

        DB_Utility.runQuery("Select count(*)  from books");
        String bookCountDBstr = DB_Utility.getFirstRowFirstColumn();
        int bookCountFromDB = Integer.parseInt(bookCountDBstr);
    //    Assert.assertEquals("Your assertion failed", bookCountFromDB, bookCountUI);

        DB_Utility.runQuery("select count(*) from users");
        int userCountDB = Integer.parseInt(DB_Utility.getFirstRowFirstColumn());
      //  Assert.assertEquals("Your assertion failed", userCountDB, userCountUI);

        DB_Utility.runQuery("select count(*) from book_borrow where returned_date is null");
        int borrowedBookCountDB = Integer.parseInt(DB_Utility.getFirstRowFirstColumn());
     //   Assert.assertEquals("Your assertion failed", borrowedBookCountDB, borrowedBookCountUI);


        DB_Utility.destroy();
    }

}
