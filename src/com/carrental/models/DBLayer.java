package com.carrental.models;

import java.sql.*;

public class DBLayer {
	public static final boolean isSQLServer = false;
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
                String host = null;
                if(isSQLServer)
                	host = "jdbc:sqlserver://localhost;databaseName=UniversityCarRental;user=sa;password=zia";
                else host = "jdbc:mysql://127.0.0.1/university_car_rental?" +
                                            "user=root&password=";
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
