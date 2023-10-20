package com.example.software2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointments {
    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDate date;
    private  LocalDate endDate;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;

    /**
     * Constructor for Appointments.
     * @param appointmentID
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy
     * @param customerID
     * @param userID
     * @param contactID
     */
    public Appointments(int appointmentID, String title, String description, String location, String type,
                        LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate,
                        String lastUpdatedBy, int customerID, int userID, int contactID) {

        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.endDate = end.toLocalDate();
    }

    /**
     * Constructor for Appointments.
     */
    public Appointments() {

    }

    /**
     * Getter for appointmentID.
     * @return
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Setter for appointmentID.
     * @param appointmentID
     */
    public void setAppointmentID(int appointmentID) {this.appointmentID = appointmentID;}

    /**
     * Getter for title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for location.
     * @return location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Setter for location.
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for start.
     * @return start
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Setter for start.
     * @param start
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Getter for end.
     * @return end
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Setter for end.
     * @param end
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Getter for date.
     * @return date
     */
    public LocalDate getDate(){ return this.date;}

    /**
     * Setter for date.
     * @param date
     */
    public void setDate(LocalDate date){ this.date = date;}

    public LocalDate getEndDate(){return this.endDate;}

    public void setEndDate(LocalDate endDate){this.endDate = endDate;}
    /**
     * Getter for createDate.
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Setter for createDate.
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * Getter for createdBy.
     * @return createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Setter for createdBy.
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Getter for lastUpdate.
     * @return lastUpdate
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Setter for lastUpdate.
     * @param lastUpdate
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Getter for lastUpdatedBy.
     * @return lastUpdatedBy
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Setter for lastUpdatedBy.
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Getter for customerID.
     * @return customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Setter for customerID.
     * @param customerID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Getter for userID.
     * @return userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID.
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for contactID.
     * @return contactID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Setter for contactID.
     * @param contactID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Getter for startTime.
     * @return startTime
     */
    public LocalTime getStartTime() {return startTime;}

    /**
     * Setter for startTime.
     * @param startTime
     */
    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}

    /**
     * Getter for endTime.
     * @return endTime.
     */
    public LocalTime getEndTime() {return endTime;}

    /**
     * Setter for endTime.
     * @param endTime
     */
    public void setEndTime(LocalTime endTime) {this.endTime = endTime;}
}
