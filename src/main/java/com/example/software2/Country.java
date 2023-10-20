package com.example.software2;

public class Country {
    private int countryID;
    private String country;

    /**
     * Contructor for Country.
     * @param countryID
     * @param country
     */
    public Country(int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }

    /**
     * Getter for countryID.
     * @return countryID
     */
    public int getCountryID(){ return this.countryID; }

    /**
     * Getter four country.
     * @return country
     */
    public String getCountry() { return this.country; }
}
