package com.cybertek.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {

    private static Connection con;
    private static Statement stm;
    private static ResultSet rs;
    private static ResultSetMetaData rsmd;

    /**
     * Create connection method , just checking one connection successful or not
     */
    public static void createConnection() {

        String url = ConfigurationReader.getProperty("hr.database.url");
        String username = ConfigurationReader.getProperty("hr.database.username");
        String password = ConfigurationReader.getProperty("hr.database.password");

//        we just call creatConnection method
//        try {
//            con = DriverManager.getConnection(url, username, password);
//            System.out.println("CONNECTION SUCCESSFUL");
//        } catch (SQLException e) {
//            System.out.println("CONNECTION HAS FAILED " + e.getMessage());
//        }

        createConnection(url, username, password);

    }

    /**
     * Create Connection by jdbc url and username , password provided
     *
     * @param url
     * @param username
     * @param password
     */
    public static void createConnection(String url, String username, String password) {

        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED " + e.getMessage());
        }
    }

    /**
     * Run the sql query provided and return ResultSet object
     *
     * @param sql
     * @return ResultSet object  that contains data
     */
    public static ResultSet runQuery(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql); // setting the value of ResultSet object
            rsmd = rs.getMetaData();    // setting the value of ResultSetMetaData for reuse
        } catch (SQLException e) {
            System.out.println("Error while running query " + e.getMessage());
        }
        return rs;
    }

    /**
     * destroy method to clean up all the resources after being used
     */
    public static void destroy() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while closing resources " + e.getMessage());
        }
    }

    //find out the row count

    /**
     * find out the row count
     *
     * @return row count of this ResultSet
     */
    public static int getRowCount() {
        int rowCount = 0;
        try {
            rs.last();
            rowCount = rs.getRow();
        } catch (SQLException e) {
            System.out.println("Error occurred while getting run count " + e.getMessage());
        } finally {
            resetCursor();
        }
        return rowCount;
    }

    /**
     * find out the column count
     *
     * @return column count of this ResultSet
     */
    public static int getColumnCount() {

        int columnCount = 0;

        try {
            columnCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("Error occurred while getting run count " + e.getMessage());
        }
        return columnCount;
    }

    //Get all the Column name into a list object
    public static List<String> getAllColumnNamesAsList() {

        List<String> columnNameList = new ArrayList<>();
        try {
            for (int colIndex = 1; colIndex <= getColumnCount(); colIndex++) {
                String columnName = rsmd.getColumnName(colIndex);
                columnNameList.add(columnName);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getAllColumnNameAsList " + e.getMessage());
        }
        return columnNameList;
    }

    //get entire row of data according to row number

    /**
     * Getting entire row of data in a List of String
     *
     * @param rowNum
     * @return row data as List of String
     */
    public static List<String> getRowDataAsList(int rowNum) {
        List<String> rowDataAsList = new ArrayList<>();
        int colCount = getColumnCount();
        try {
            rs.absolute(rowNum);
            for (int colIndex = 1; colIndex <= colCount; colIndex++) {
                String cellValue = rs.getString(colIndex);
                rowDataAsList.add(cellValue);
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while getRowDataAsList " + e.getMessage());
        } finally {
            resetCursor();
        }

        return rowDataAsList;
    }

    /**
     * getting the cell value according to row num and column index
     *
     * @param rowNum
     * @param columnIndex
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum, int columnIndex) {

        String cellValue = "";
        try {
            rs.absolute(rowNum);
            cellValue = rs.getString(columnIndex);
        } catch (SQLException e) {
            System.out.println("Error occurred while getCellValue " + e.getMessage());
        } finally {
            resetCursor();
        }
        return cellValue;
    }

    /**
     * getting the cell value according to row num and column name
     *
     * @param rowNum
     * @param columnName
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum, String columnName) {
        String cellValue = "";
        try {
            rs.absolute(rowNum);
            cellValue = rs.getString(columnName);
        } catch (SQLException e) {
            System.out.println("Error occurred while getCellValue " + e.getMessage());
        } finally {
            resetCursor();
        }

        return cellValue;

    }

    /**
     * Get First Cell Value at First row First Column
     */
    public static String getFirstRowFirstColumn() {

        return getCellValue(1, 1);

    }

    /*
     * getting the cell value according to row num and column name
     * @param rowNum
     * @param columnName
     * @return the value in String at that location
     */
    public static List<String> getColumnDataAsList(int columnNum) {

        List<String> columnDataList = new ArrayList<>();

        try {
            rs.beforeFirst();   //make sure the cursor is at before first location
            while (rs.next()) {

                String cellValue = rs.getString(columnNum);
                columnDataList.add(cellValue);
            }
            rs.beforeFirst();   //make sure to reset the cursor to before first location

        } catch (SQLException e) {
            System.out.println("Error occurred while getColumnDataAsList " + e.getMessage());
        } finally {
            resetCursor();
        }

        return columnDataList;

    }

    /**
     * getting entire column data as list according to column Name
     *
     * @param columnName
     * @return List object that contains all rows of that column
     */
    public static List<String> getColumnDataAsList(String columnName) {

        List<String> columnDataLst = new ArrayList<>();

        try {
            rs.beforeFirst(); // make sure the cursor is at before first location
            while (rs.next()) {

                String cellValue = rs.getString(columnName);
                columnDataLst.add(cellValue);
            }
            rs.beforeFirst(); // make sure to reset the cursor to before first location

        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage());
        } finally {
            resetCursor();
        }

        return columnDataLst;
    }

    /*
    this method will reset cursor to before first location
     */
    private static void resetCursor() {
        try {
            rs.beforeFirst();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*
     *display all data from the ResultSet object
     */
    public static void displayAllData() {

        int columnCount = getColumnCount();
        resetCursor();
        try {
            while (rs.next()) {
                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                    // System.out.print(rs.getString(colIndex) + " ");
                    System.out.printf("%-35s", rs.getString(colIndex));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while displayAllData " + e.getMessage());
        } finally {
            resetCursor();
        }
    }

    /**
     * Save entire row data as Map<String,String>
     *
     * @param rowNum
     * @return Map object that contains key value pair
     * key     : column name
     * value   : cell value
     */

    public static Map<String, String> getRowMap(int rowNum) {
        Map<String, String> rowMap = new LinkedHashMap<>();


        int columnCount = getColumnCount();

        try {
            rs.absolute(rowNum);
            for (int colIndex = 1; colIndex <= columnCount; colIndex++) {
                String columnName = rsmd.getColumnName(colIndex);
                String cellValue = rs.getString(colIndex);
                rowMap.put(columnName, cellValue);
            }

        } catch (SQLException e) {
            System.out.println("Error occurred while getRowMap " + e.getMessage());
        } finally {
            resetCursor();
        }

        return rowMap;
    }

    public static List<Map<String, String>> getAllRowAsListOfMap() {
        List<Map<String, String>> allRowListOfMap = new ArrayList<>();
        int rowCount = getRowCount();

        //move from first row till last row
        //get each row as map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
            Map<String, String> rowMap = getRowMap(rowIndex);
            allRowListOfMap.add(rowMap);

        }
        resetCursor();

        return allRowListOfMap;
    }

    public static Map<String,String> getColumnDataAsMap(String columnName1 ,String columnName2){
        Map<String,String> map=new LinkedHashMap<>();
        try {
            rs.beforeFirst(); // make sure the cursor is at before first location
            while( rs.next() ){
                String cellValue1 = rs.getString(columnName1) ;
                String value2 =rs.getString(columnName2);
                map.put(cellValue1,value2 );
            }
        } catch (SQLException e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return map;
    }

}

