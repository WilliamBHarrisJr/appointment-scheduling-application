package com.example.software2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DataList {
    private final ObservableList<LocalTime> timeList = FXCollections.observableArrayList();

    /**
     * Constructor for DataList.
     */
    public DataList(){}

    /**
     * Gets list of hours for combo-box items.
     * @return ObservableList of hours
     */
    public static ObservableList<Integer> getHourList(){
        ObservableList<Integer> hourList = FXCollections.observableArrayList();
        hourList.add(0);
        hourList.add(1);
        hourList.add(2);
        hourList.add(3);
        hourList.add(4);
        hourList.add(5);
        hourList.add(6);
        hourList.add(7);
        hourList.add(8);
        hourList.add(9);
        hourList.add(10);
        hourList.add(11);
        hourList.add(12);
        hourList.add(13);
        hourList.add(14);
        hourList.add(15);
        hourList.add(16);
        hourList.add(17);
        hourList.add(18);
        hourList.add(19);
        hourList.add(20);
        hourList.add(21);
        hourList.add(22);
        hourList.add(23);
        return hourList;
    }

    /**
     * Gets list of minutes for combo-box items.
     * @return ObservableList of minutes
     */
    public static ObservableList<String> getMinuteList(){
        ObservableList<String> minuteList = FXCollections.observableArrayList();
        minuteList.add("00");
        minuteList.add("01");
        minuteList.add("03");
        minuteList.add("04");
        minuteList.add("05");
        minuteList.add("06");
        minuteList.add("07");
        minuteList.add("08");
        minuteList.add("09");
        minuteList.add("10");
        minuteList.add("11");
        minuteList.add("12");
        minuteList.add("13");
        minuteList.add("14");
        minuteList.add("15");
        minuteList.add("16");
        minuteList.add("17");
        minuteList.add("18");
        minuteList.add("19");
        minuteList.add("20");
        minuteList.add("21");
        minuteList.add("22");
        minuteList.add("23");
        minuteList.add("24");
        minuteList.add("25");
        minuteList.add("26");
        minuteList.add("27");
        minuteList.add("28");
        minuteList.add("29");
        minuteList.add("30");
        minuteList.add("31");
        minuteList.add("32");
        minuteList.add("33");
        minuteList.add("34");
        minuteList.add("35");
        minuteList.add("36");
        minuteList.add("37");
        minuteList.add("38");
        minuteList.add("39");
        minuteList.add("40");
        minuteList.add("41");
        minuteList.add("42");
        minuteList.add("43");
        minuteList.add("44");
        minuteList.add("45");
        minuteList.add("46");
        minuteList.add("47");
        minuteList.add("48");
        minuteList.add("49");
        minuteList.add("50");
        minuteList.add("51");
        minuteList.add("52");
        minuteList.add("53");
        minuteList.add("54");
        minuteList.add("55");
        minuteList.add("56");
        minuteList.add("57");
        minuteList.add("58");
        minuteList.add("59");
        return minuteList;
    }

    /**
     * Gets list of types for combo-box items.
     * @return ObservableList of types.
     */
    public static ObservableList<String> getTypeList(Data data){
        ObservableList<String> typeList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT DISTINCT MeetingType FROM meeting_types";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                typeList.add(rs.getString("MeetingType"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return typeList;
    }

    /**
     * Gets list of report types for combo-box items.
     * @return ObservableList of report types
     */
    public static ObservableList<String> getReportTypeList(){
        ObservableList<String> reportTypeList = FXCollections.observableArrayList();
        reportTypeList.add("Contact Schedule");
        reportTypeList.add("Customers by Type");
        reportTypeList.add("Customers by Month");
        reportTypeList.add("Customers by Country");
        return reportTypeList;
    }

    /**
     * Gets list of months for combo-box items.
     * @return ObservableList of months
     */
    public static ObservableList<String> getMonthList(){
        ObservableList<String> monthList = FXCollections.observableArrayList();
        monthList.add("January");
        monthList.add("February");
        monthList.add("March");
        monthList.add("April");
        monthList.add("May");
        monthList.add("June");
        monthList.add("July");
        monthList.add("August");
        monthList.add("September");
        monthList.add("October");
        monthList.add("November");
        monthList.add("December");
        return monthList;
    }

    /**
     *Converts LocalDateTime from UTC to system default time zone.
     * @param ldt LocalDateTime in UTC.
     * @return LocalDateTime in system default time zone.
     */
    public static LocalDateTime UTCtoLocal(LocalDateTime ldt){
        ZonedDateTime UTC = ZonedDateTime.of(ldt, ZoneId.of("UTC"));
        ZonedDateTime Local = UTC.withZoneSameInstant(ZoneId.systemDefault());
        return Local.toLocalDateTime();
    }

    /**
     * Creates SQL query to retrieve list of appointments.
     * @param data
     * @return ObservableList of appointments
     */
    public static ObservableList<Appointments> getAppointmentList(Data data){
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointments appointments;
            while(rs.next()){
                appointments = new Appointments(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Start"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("End"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)), rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));

                appointments.setStartTime(appointments.getStart().toLocalTime());
                appointments.setEndTime(appointments.getEnd().toLocalTime());
                appointments.setDate(appointments.getStart().toLocalDate());
                appointmentList.add(appointments);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Creates SQL query to retrieve list of customers.
     * @param data
     * @return ObservableList of customers.
     */
    public static ObservableList<Customers> getCustomerList(Data data){
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM customers";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Customers customers;
            while(rs.next()){
                customers = new Customers(rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)).toLocalDate(), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)).toLocalDate(), rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID"), DataList.getDivisionName(data, rs.getInt("Division_ID")),
                        DataList.getCountryName(data, rs.getInt("Division_ID")));
                customerList.add(customers);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    /**
     * Creates SQL query to retrieve list of customer IDs.
     * @param data
     * @return ObservableList of customer IDs
     */
    public static ObservableList<Integer> getCustomerIDList(Data data){
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM customers";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int customerID;
            while(rs.next()){
                customerID = rs.getInt("Customer_ID");
                customerIDList.add(customerID);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return customerIDList;
    }

    /**
     * Creates SQL query to retrieve list of contact ID integers.
     * @param data
     * @return ObservableList of contact ID integers
     */
    public static ObservableList<Integer> getContactIDList(Data data){
        ObservableList<Integer> contactIDList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM contacts";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int contactID;
            while(rs.next()){
                contactID = rs.getInt("Contact_ID");
                contactIDList.add(contactID);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return contactIDList;
    }

    /**
     * Creates SQL query to retrieve list of contact ID strings.
     * @param data
     * @return ObservableList of customer ID strings
     */
    public static ObservableList<String> getContactIDStringList(Data data){
        ObservableList<String> contactIDList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM contacts";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String contactID;
            while(rs.next()){
                contactID = rs.getString("Contact_ID");
                contactIDList.add(contactID);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return contactIDList;
    }

    /**
     * Creates SQL query to retrieve list of countries.
     * @param data
     * @return ObservableList of countries
     */
    public static ObservableList<Country> getCountryList(Data data){
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM countries";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Country country = new Country(rs.getInt("Country_ID"), rs.getString("country"));
                countryList.add(country);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return countryList;
    }

    /**
     * Creates SQL query to retrieve list of country names.
     * @param data
     * @return ObservableList of country names
     */
    public static ObservableList<String> getCountryNameList(Data data){
        ObservableList<Country> countryList = FXCollections.observableArrayList();
        ObservableList<String> countryNameList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM countries";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Country country = new Country(rs.getInt("Country_ID"), rs.getString("country"));
                countryList.add(country);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        for (Country c: countryList
             ) {
            countryNameList.add(c.getCountry());
        }
        return countryNameList;
    }

    /**
     * Creates SQL query to retrieve country name by division ID.
     * @param data
     * @param divisionID
     * @return Country name
     */
    public static String getCountryName(Data data, int divisionID){
        ObservableList<Division> divisionList = DataList.getDivisionList(data);
        ObservableList<Country> countryList = DataList.getCountryList(data);
        String countryName = "";
        int countryID = 0;
        for (Division d: divisionList
        ) {
            if(d.getDivisionID() == (divisionID)){
                countryID = d.getCountryID();
            }
        }
        for (Country c: countryList
             ) {
            if(c.getCountryID() == countryID){
                countryName = c.getCountry();
            }
        }
        return countryName;
    }

    /**
     * Creates SQL query to retrieve list of division IDs.
     * @param data
     * @return ObservableList of division IDs
     */
    public static ObservableList<Division> getDivisionList(Data data){
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM first_level_divisions";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                Division division = new Division(rs.getInt("Division_ID"),
                        rs.getString("Division"), rs.getInt("Country_Id"));
                divisionList.add(division);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return divisionList;
    }

    /**
     * Creates SQL query to retrieve list of division names.
     * @param data
     * @param country
     * @return ObservableList of division names
     */
    public static ObservableList<String> getDivisionNameList(Data data, String country){
        ObservableList<String> divisionList = FXCollections.observableArrayList();
        ObservableList<Country> countryList = DataList.getCountryList(data);
        int countryID = 0;
        for (Country c: countryList
             ) {
            if(c.getCountry().equals(country)){
                countryID = c.getCountryID();
            }
        }
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryID;
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                String division = rs.getString("Division");
                divisionList.add(division);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return divisionList;
    }

    /**
     * Creates SQL query to retrieve list of division IDs from a country.
     * @param data
     * @param country
     * @return ObservableList of division IDs
     */
    public static ObservableList<Integer> getDivisionIDListFromCountry(Data data, String country){
        ObservableList<Integer> divisionList = FXCollections.observableArrayList();
        ObservableList<Country> countryList = DataList.getCountryList(data);
        int countryID = 0;
        for (Country c: countryList
        ) {
            if(c.getCountry().equals(country)){
                countryID = c.getCountryID();
            }
        }
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM first_level_divisions WHERE Country_ID = " + countryID;
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                int division = rs.getInt("Division_ID");
                divisionList.add(division);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return divisionList;
    }

    /**
     * Creates SQL query to retrieve a division ID from division name.
     * @param data
     * @param division
     * @return Division ID
     */
    public static int getDivisionID(Data data, String division){
        ObservableList<Division> divisionList = DataList.getDivisionList(data);
        int divisionID = 0;
        for (Division d: divisionList
        ) {
            if(d.getDivision().equals(division)){
                divisionID = d.getDivisionID();
            }
        }
        return divisionID;
    }

    /**
     * Creates SQL query to division name from division ID.
     * @param data
     * @param divisionID
     * @return Division name
     */
    public static String getDivisionName(Data data, int divisionID){
        ObservableList<Division> divisionList = DataList.getDivisionList(data);
        String divisionName = "";
        for (Division d: divisionList
        ) {
            if(d.getDivisionID() == (divisionID)){
                divisionName = d.getDivision();
            }
        }
        return divisionName;
    }

    /**
     * Generates next appointment ID.
     * @param data
     * @return New appointment ID
     */
    public static int generateAppointmentID(Data data){
        ObservableList<Integer> appointmentIDList = FXCollections.observableArrayList();
        int newAppointmentID = 0;
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int appointmentID;
            while(rs.next()){
                appointmentID = rs.getInt("Appointment_ID");
                appointmentIDList.add(appointmentID);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(!appointmentIDList.isEmpty()){
            newAppointmentID = Collections.max(appointmentIDList) + 1;
        }
        return newAppointmentID;
    }

    /**
     * Generates next customer ID.
     * @param data
     * @return New customer ID
     */
    public static int generateCustomerID(Data data){
        ObservableList<Integer> customerIDList = FXCollections.observableArrayList();
        int newCustomerID = 0;
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM customers";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int customerID;
            while(rs.next()){
                customerID = rs.getInt("Customer_ID");
                customerIDList.add(customerID);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(!customerIDList.isEmpty()){
            newCustomerID = Collections.max(customerIDList) + 1;
        }
        return newCustomerID;
    }

    /**
     * Creates SQL query to retrieve list of appointments from contact ID.
     * @param data
     * @param contactID
     * @return ObservableList of appointments
     */
    public static ObservableList<Appointments> getAppointmentListByContactID(Data data, int contactID){
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments WHERE Contact_ID = " + contactID;
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointments appointments;
            while(rs.next()){
                appointments = new Appointments(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Start"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("End"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)), rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));

                appointments.setStartTime(appointments.getStart().toLocalTime());
                appointments.setEndTime(appointments.getEnd().toLocalTime());
                appointments.setDate(appointments.getStart().toLocalDate());
                appointmentList.add(appointments);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Creates SQL query to retrieve list of appointments from appointment type.
     * @param data
     * @param type
     * @return ObservableList of appointments
     */
    public static ObservableList<Appointments> getAppointmentListByType(Data data, String type){
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments WHERE Type = '" + type + "'";
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointments appointments;
            while(rs.next()){
                appointments = new Appointments(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Start"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("End"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)), rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));

                appointments.setStartTime(appointments.getStart().toLocalTime());
                appointments.setEndTime(appointments.getEnd().toLocalTime());
                appointments.setDate(appointments.getStart().toLocalDate());
                appointmentList.add(appointments);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Creates SQL query to retrieve list of appointments from month.
     * @param data
     * @param monthName
     * @return ObservableList of appointments
     */
    public static ObservableList<Appointments> getAppointmentListByMonth(Data data, String monthName){
        Month month = Month.valueOf(monthName.toUpperCase());
        int monthNum =month.getValue();
        int year = Year.now().getValue();
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments WHERE MONTH(Start) = " + monthNum + " AND YEAR(Start) = " + year;
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointments appointments;
            while(rs.next()){
                appointments = new Appointments(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Start"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("End"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)), rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));

                appointments.setStartTime(appointments.getStart().toLocalTime());
                appointments.setEndTime(appointments.getEnd().toLocalTime());
                appointments.setDate(appointments.getStart().toLocalDate());
                appointmentList.add(appointments);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Creates SQL query to retrieve list of appointments for current month.
     * @param data
     * @return ObservableList of appointments
     */
    public static ObservableList<Appointments> getAppointmentListForCurrentMonth(Data data){
        int month = LocalDate.now().getMonthValue();
        int year = Year.now().getValue();
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments WHERE MONTH(Start) = " + month + " AND YEAR(Start) = " + year;
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointments appointments;
            while(rs.next()){
                appointments = new Appointments(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Start"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("End"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)), rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));

                appointments.setStartTime(appointments.getStart().toLocalTime());
                appointments.setEndTime(appointments.getEnd().toLocalTime());
                appointments.setDate(appointments.getStart().toLocalDate());
                appointmentList.add(appointments);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * Creates SQL query to retrieve list of appointments for current week.
     * @param data
     * @return ObservableList of appointments
     */
    public static ObservableList<Appointments> getAppointmentListForCurrentWeek(Data data){
        int currentWeek = LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM appointments";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Appointments appointments;
            while(rs.next()){
                appointments = new Appointments(rs.getInt("Appointment_ID"), rs.getString("Title"),
                        rs.getString("Description"), rs.getString("Location"), rs.getString("Type"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Start"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("End"), data.formatter)),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)), rs.getString("Last_Updated_By"),
                        rs.getInt("Customer_ID"), rs.getInt("User_ID"), rs.getInt("Contact_ID"));
                appointments.setStartTime(appointments.getStart().toLocalTime());
                appointments.setEndTime(appointments.getEnd().toLocalTime());
                appointments.setDate(appointments.getStart().toLocalDate());
                LocalDateTime appointmentDate = appointments.getStart();
                int weekNumber = appointmentDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
                if(currentWeek == weekNumber){
                    appointmentList.add(appointments);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return appointmentList;
    }

    /**
     * *LAMBDA*
     * The use of the lambda expressions with stream to reduce the amount of code needed to iterate through the list.
     * Creates SQL query to retrieve list of customers from type of appointment.
     * @param data
     * @param type
     * @return ObservableList of customers
     */
    public static ObservableList<Customers> getCustomerListByType(Data data, String type){
        ObservableList<Appointments> appointmentList = getAppointmentListByType(data, type);
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
        Set<Integer> customerIDSet = new HashSet<>();
        appointmentList.stream().forEach((a) -> customerIDSet.add(a.getCustomerID()));
        customerIDSet.forEach((i) -> customerList.addAll(getCustomerListByCustomerID(data, i)));
        return customerList;
    }

    /**
     * *LAMBDA*
     * The use of the lambda expressions reduce the amount of code needed to iterate through the list.
     * Creates SQL query to retrieve list of customers from month of appointment.
     * @param data
     * @param month
     * @return ObservableList of customers
     */
    public static ObservableList<Customers> getCustomerListByMonth(Data data, String month){
        ObservableList<Appointments> appointmentList = getAppointmentListByMonth(data, month);
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
        Set<Integer> customerIDSet = new HashSet<>();
        appointmentList.forEach((a) -> customerIDSet.add(a.getCustomerID()));
        customerIDSet.forEach((i) -> customerList.addAll(getCustomerListByCustomerID(data, i)));
        return customerList;
    }

    /**
     * Creates SQL query to retrieve list of customers from customer ID.
     * @param data
     * @param customerID
     * @return ObservableList of customers
     */
    public static ObservableList<Customers> getCustomerListByCustomerID(Data data, int customerID){
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
        Connection conn = data.connection.openConnection();
        String query = "SELECT * FROM customers WHERE Customer_ID = " + customerID;
        Statement st;
        ResultSet rs;
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Customers customers;
            while(rs.next()){
                customers = new Customers(rs.getInt("Customer_ID"),
                        rs.getString("Customer_Name"),
                        rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)).toLocalDate(), rs.getString("Created_By"),
                        UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)).toLocalDate(), rs.getString("Last_Updated_By"),
                        rs.getInt("Division_ID"), DataList.getDivisionName(data, rs.getInt("Division_ID")),
                        DataList.getCountryName(data, rs.getInt("Division_ID")));
                customerList.add(customers);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return customerList;
    }

    /**
     * Creates SQL query to retrieve list of customers from country.
     * @param data
     * @param country
     * @return ObservableList of customers
     */
    public static ObservableList<Customers> getCustomerListByCountry(Data data, String country){
        ObservableList<Customers> customerList = FXCollections.observableArrayList();
        ObservableList<Integer> divisionIDList = DataList.getDivisionIDListFromCountry(data, country);
        for (int i : divisionIDList
             ) {
            Connection conn = data.connection.openConnection();
            String query = "SELECT * FROM customers WHERE Division_ID = " + i;
            Statement st;
            ResultSet rs;
            try{
                st = conn.createStatement();
                rs = st.executeQuery(query);
                Customers customers;
                while(rs.next()){
                    customers = new Customers(rs.getInt("Customer_ID"),
                            rs.getString("Customer_Name"),
                            rs.getString("Address"), rs.getString("Postal_Code"), rs.getString("Phone"),
                            UTCtoLocal(LocalDateTime.parse(rs.getString("Create_Date"), data.formatter)).toLocalDate(), rs.getString("Created_By"),
                            UTCtoLocal(LocalDateTime.parse(rs.getString("Last_Update"), data.formatter)).toLocalDate(), rs.getString("Last_Updated_By"),
                            rs.getInt("Division_ID"), DataList.getDivisionName(data, rs.getInt("Division_ID")),
                            DataList.getCountryName(data, rs.getInt("Division_ID")));
                    customerList.add(customers);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return customerList;
    }
}


