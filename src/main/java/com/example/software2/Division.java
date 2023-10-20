package com.example.software2;

public class Division {
    private int divisionID;
    private String division;
    private int countryID;

    /**
     * Constructor for Division.
     * @param divisionID
     * @param division
     * @param countryID
     */
    public Division(int divisionID, String division, int countryID){
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * Getter for divisionID.
     * @return divisionID
     */
    public int getDivisionID(){ return this.divisionID; }

    /**
     * Getter for division name.
     * @return division
     */
    public String getDivision() { return this.division; }

    /**
     * Getter for countryID.
     * @return
     */
    public int getCountryID() { return this.countryID; }
}
