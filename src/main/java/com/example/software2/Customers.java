package com.example.software2;

import java.time.LocalDate;

public class Customers {
    private int iD;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private LocalDate createDate;
    private String createdBy;
    private LocalDate lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    private String division;
    private String country;

    /**
     * Constructor for Customers.
     */
    public Customers(){};

    /**
     * Constructor for Customers.
     */
    public Customers(int iD, String name, String address, String postalCode, String phone, LocalDate createDate,
                          String createdBy, LocalDate lastUpdate, String lastUpdatedBy, int divisionId, String division, String country){
        this.iD = iD;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
    }

    /**
     * Getter for iD.
     * @return id
     */
    public int getID(){
        return this.iD;
    }

    /**
     * Setter for iD.
     * @param iD
     */
    public void setID(int iD){this.iD = iD;}

    /**
     * Getter for name.
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Setter for name.
     * @param name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Getter for phone.
     * @return phone
     */
    public String getPhone(){
        return this.phone;
    }

    /**
     * Setter for phone.
     * @param phone
     */
    public void setPhone(String phone){this.phone = phone;}

    /**
     * Getter for address.
     * @return address
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * Setter for address.
     * @param address
     */
    public void setAddress(String address){this.address = address;}

    /**
     * Getter for postalCode.
     * @return postalCode
     */
    public String getPostalCode(){
        return this.postalCode;
    }

    /**
     * Setter for postalCode.
     * @param postalCode
     */
    public void setPostalCode(String postalCode){this.postalCode = postalCode;}

    /**
     * Getter for createDate.
     * @return createDate
     */
    public LocalDate getCreateDate(){return this.createDate;}

    /**
     * Setter for createDate.
     * @param createDate
     */
    public void setCreateDate(LocalDate createDate){this.createDate = createDate;}

    /**
     * Getter for createdBy.
     * @return createdBy
     */
    public String getCreatedBy(){return this.createdBy;}

    /**
     * Setter for createdBy.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy){this.createdBy = createdBy;}

    /**
     * Getter for lastUpdate.
     * @return lastUpdate
     */
    public LocalDate getLastUpdate(){return this.lastUpdate;}

    /**
     * Setter for lastUpdate.
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDate lastUpdate){this.lastUpdate = lastUpdate;}

    /**
     * Getter for lastUpdatedBy.
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy(){return  this.lastUpdatedBy;}

    /**
     * Setter for lastUpdatedBy.
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy){this.lastUpdatedBy =lastUpdatedBy;}

    /**
     * Getter for divisionID.
     * @return divisionID
     */
    public int getDivisionId(){
        return  this.divisionId;
    }

    /**
     * Setter for divisionID.
     * @param divisionId
     */
    public void setDivisionId(int divisionId){this.divisionId = divisionId;}

    /**
     * Getter for division.
     * @return division
     */
    public String getDivision(){
        return  this.division;
    }

    /**
     * Setter for division.
     * @param division
     */
    public void setDivision(String division){this.division = division;}

    /**
     * Getter for country.
     * @return country
     */
    public String getCountry(){
        return  this.country;
    }

    /**
     * Setter for country.
     * @param country
     */
    public void setCountry(String country){this.country = country;}
}



