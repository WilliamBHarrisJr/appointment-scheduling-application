package com.example.software2;

public class User {
    private String userName;
    private int userID;

    /**
     * Constructor for User.
     * @param userID
     * @param userName
     */
    public User(int userID, String userName){
        this.userID = userID;
        this.userName = userName;
    }

    /**
     * Constructor for User.
     */
    public User(){
    }

    /**
     * Getter for userName.
     * @return userName
     */
    public String getUserName() {return userName;}

    /**
     * Setter for userName.
     * @param userName
     */
    public void setUserName(String userName) {this.userName = userName;}

    /**
     * Getter for userID.
     * @return UserID
     */
    public int getUserID() {return userID;}

    /**
     * Setter for userID.
     * @param userID
     */
    public void setUserID(int userID) {this.userID = userID;}
}
