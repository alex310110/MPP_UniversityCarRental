package com.carrental.models;

import java.sql.*;

public class DBLayer {
    private static DBLayer ourInstance = new DBLayer();
    private static Connection con;
    private static Statement stmt;
    public static DBLayer getInstance() {
        return ourInstance;
    }
    public static Connection getConnection() {
        return con;
    }

    private DBLayer(){}

    public static void newDBLayer()
    {
        if(con == null)
        {
            try
            {
                String host = "jdbc:sqlserver://localhost;databaseName=UniversityCarRental;user=sa;password=zia";
                con = DriverManager.getConnection(host);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
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
