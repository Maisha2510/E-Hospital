package com.example.ehospital;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
    public static Connection con;
    public static void  Connection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost:1433;user=team;password=p@ssword13;databaseName=EHdb;";

            con = DriverManager.getConnection(connectionUrl);

            System.out.println("Connected");

//             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

