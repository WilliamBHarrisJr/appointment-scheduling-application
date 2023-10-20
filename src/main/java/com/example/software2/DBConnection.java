package com.example.software2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    Connection connection;

    /**
     * Opens connection to database.
     * @return
     */
    public Connection openConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/planner", "root", "Kittenpants5975");
            return connection;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Executes query to database.
     * @param query
     */
    public void executeQuery(String query) {
        connection = openConnection();
        Statement st;
        try {
            st = connection.createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Closes database connection.
     */
    public void closeConnection(){
        try {
            connection.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
