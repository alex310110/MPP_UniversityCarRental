package com.carrental.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DBLayer {
	public static final boolean isSQLServer = true;
    private static DBLayer ourInstance = new DBLayer();
    private static Connection con;
    public static DBLayer getInstance() {
        return ourInstance;
    }
    public static Connection getConnection() {
        return con;
    }
    public static void ReadConnectionStringFromFile()
    {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br =new BufferedReader(new FileReader(new File("App.Config.txt"))))
        {

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            String everything = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = sb.toString();

/*
        if(isSQLServer)
            host = "jdbc:sqlserver://DESKTOP-R9V9595\\ZIALT;;databaseName=UniversityCarRental;user=sa;password=zia";
        else host = "jdbc:mysql://127.0.0.1/university_car_rental?" +
                "user=root&password=";
*/
        try {
            con = DriverManager.getConnection(host);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private DBLayer(){}

    public static void newDBLayer()
    {
        if(con == null)
            ReadConnectionStringFromFile();
    }
    
    public static ResultSet ExecuteSQL(String sqlStatement)
    {
        try {
            if (con == null) {
                newDBLayer();
            }
            Statement stmt = con.createStatement();
            String SQL = sqlStatement;
            ResultSet rs = stmt.executeQuery(SQL);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
           return null;
        }
    }
    
    public static void ExecuteQuery(String sqlStatement)
    {
        try {
            if (con == null) {
                newDBLayer();
            }
            Statement stmt = con.createStatement();
            String SQL = sqlStatement;
            stmt.execute(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
