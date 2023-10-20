package com.example.software2;

import java.time.format.DateTimeFormatter;

public class Data {
    private static final Data instance = new Data();
    DBConnection connection = new DBConnection();
    private String user;
    private int userID;

    /**
     * Formatter for DateTime.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructure for Date.
     */
    private Data(){}

    /**
     * Getter for instance.
     * @return instance
     */
    public static Data getInstance(){
        return instance;
    }

    /**
     * Setter for data.
     * @param data
     */
    public void setData(Data data){
        this.user = data.getUser();
        this.userID = data.getUserID();
    }

    /**
     * Getter for user.
     * @return user
     */
    public String getUser(){
        return user;
    }

    /**
     * Setter for user.
     * @param user
     */
    public void setUser(String user){
        this.user = user;
    }

    /**
     * Getter for userID.
     * @return userID
     */
    public int getUserID() {return userID;}

    /**
     * Setter for userID.
     * @param userID
     */
    public void setUserID(int userID) {this.userID = userID;}

}
