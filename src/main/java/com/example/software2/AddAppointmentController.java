package com.example.software2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.*;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {
    Data data = Data.getInstance();
    DBConnection connection = new DBConnection();

    /**
     * GUI text field.
     */
    @FXML private TextField tfAppointmentID;

    /**
     * GUI text field.
     */
    @FXML private TextField tfTitle;

    /**
     * GUI text field.
     */
    @FXML private  TextField tfDescription;

    /**
     * GUI text field.
     */
    @FXML private TextField tfLocation;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbType;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<Integer> cbContactID;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<Integer> cbCustomerID;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<Integer> cbStartTimeHour;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbStartTimeMinute;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<Integer> cbEndTimeHour;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbEndTimeMinute;

    /**
     * GUI date-picker.
     */
    @FXML private DatePicker dpStartDate;

    /**
     * GUI date-picker.
     */
    @FXML private DatePicker dpEndDate;

    /**
     * Inserts new appointment into database, sends data to and goes to main screen.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void save(ActionEvent event) throws IOException, SQLException {
        if(inputErrorCheck()){
            insertAppointment();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainScreen-view.fxml"));
            Parent parent = loader.load();

            MainScreenController controller = loader.getController();
            controller.data.setData(this.data);

            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
            window.getIcons().add(icon);
            window.setTitle("Appointment Scheduler");
            window.setScene(scene);
            window.show();
        }
    }

    /**
     * Goes back to main screen without saving.
     * @param event
     * @throws IOException
     */
    public void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen-view.fxml"));
        Parent parent = loader.load();

        MainScreenController controller = loader.getController();
        controller.data.setData(this.data);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
        window.getIcons().add(icon);
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    /**
     *Converts LocalDateTime from system default time zone to UTC.
     * @param ldt LocalDateTime in system default time zone.
     * @return LocalDateTime in UTC.
     */
    public static LocalDateTime localToUTC(LocalDateTime ldt){
        ZonedDateTime Local = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime UTC = Local.withZoneSameInstant(ZoneId.of("UTC"));
        return UTC.toLocalDateTime();
    }

    /**
     * Creates SQL query to insert new appointment into database.
     */
    public void insertAppointment(){
        java.sql.Date date = java.sql.Date.valueOf(localToUTC(LocalDateTime.now()).toLocalDate());
        LocalTime startTimeLocal = LocalTime.of(cbStartTimeHour.getValue(), Integer.parseInt(cbStartTimeMinute.getValue()));
        LocalDateTime startDateTimeLocal = LocalDateTime.of(dpStartDate.getValue(), startTimeLocal);
        LocalTime stopTimeLocal = LocalTime.of(cbEndTimeHour.getValue(), Integer.parseInt(cbEndTimeMinute.getValue()));
        LocalDateTime stopDateTimeLocal = LocalDateTime.of(dpEndDate.getValue(), stopTimeLocal);

        String query = "INSERT INTO appointments VALUES ("+ tfAppointmentID.getText()+",'"+tfTitle.getText()+"','"+tfDescription.getText()+
                "','"+tfLocation.getText()+"','"+ cbType.getValue()+"','"+
                localToUTC(startDateTimeLocal).toLocalDate()+" "+ localToUTC(startDateTimeLocal).toLocalTime() +"','"+
                localToUTC(stopDateTimeLocal).toLocalDate()+" "+ localToUTC(stopDateTimeLocal).toLocalTime() +"','"+
                date+"','"+data.getUser()+"','"+date+"','" + data.getUser()+"',"+cbCustomerID.getValue()+","+
                data.getUserID()+","+cbContactID.getValue()+");";

        connection.executeQuery(query);
    }

    FI setAppointmentID = () -> tfAppointmentID.setText(Integer.toString(DataList.generateAppointmentID(data)));



    /**
     * Initializes combo-box items and appointment ID field.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAppointmentID.call();

        cbStartTimeHour.setItems(DataList.getHourList());
        cbStartTimeMinute.setItems(DataList.getMinuteList());
        cbEndTimeHour.setItems(DataList.getHourList());
        cbEndTimeMinute.setItems(DataList.getMinuteList());
        cbType.setItems(DataList.getTypeList(data));
        cbCustomerID.setItems(DataList.getCustomerIDList(data));
        cbContactID.setItems(DataList.getContactIDList(data));
    }

    /**
     * Checks all inputs for errors and conflicts.
     * @return
     */
    public boolean inputErrorCheck(){
        boolean isGood = true;
        boolean isDateTimeGood = true;
        AlertBox alert = new AlertBox("Appointment Error", "Error Adding Appointment");

        if(tfLocation.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Appointment Location is Needed");
        }
        if(tfDescription.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Appointment Description is Needed");
        }
        if(tfTitle.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Appointment Title is Needed");
        }
        try {
            dpStartDate.getValue();
        }catch (Exception ex){
            isGood = false;
            isDateTimeGood = false;
            alert.addMessage("Valid Start Date is Needed");
        }
        try{
            dpEndDate.getValue();
        }catch (Exception ex){
            isGood = false;
            isDateTimeGood = false;
            alert.addMessage("Valid End Date is Needed");
        }
        if(cbEndTimeHour.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            isDateTimeGood = false;
            alert.addMessage("End Hour is Needed");
        }
        if(cbEndTimeMinute.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            isDateTimeGood = false;
            alert.addMessage("End Minute is Needed");
        }
        if(cbStartTimeHour.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            isDateTimeGood = false;
            alert.addMessage("Start Hour is Needed");
        }
        if(cbStartTimeMinute.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            isDateTimeGood = false;
            alert.addMessage("Start Minute is Needed");
        }
        if(cbType.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            alert.addMessage("Appointment Type is Needed");
        }
        if(cbContactID.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            alert.addMessage("Appointment Contact ID is Needed");
        }
        if(cbCustomerID.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            alert.addMessage("Appointment Customer ID is Needed");
        }

        if(isDateTimeGood){
            LocalTime startTimeLocal = LocalTime.of(cbStartTimeHour.getValue(), Integer.parseInt(cbStartTimeMinute.getValue()));
            ZonedDateTime startDateTimeLocal = ZonedDateTime.of(dpStartDate.getValue(), startTimeLocal, ZoneId.systemDefault());
            ZonedDateTime startDateTimeEST = startDateTimeLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
            LocalTime endTimeLocal = LocalTime.of(cbEndTimeHour.getValue(), Integer.parseInt(cbEndTimeMinute.getValue()));
            ZonedDateTime endDateTimeLocal = ZonedDateTime.of(dpEndDate.getValue(), endTimeLocal, ZoneId.systemDefault());
            ZonedDateTime endDateTimeEST = endDateTimeLocal.withZoneSameInstant(ZoneId.of("America/New_York"));
            ZonedDateTime businessOpenEST = ZonedDateTime.of(startDateTimeEST.toLocalDate(), LocalTime.of(8, 00), ZoneId.of("America/New_York"));
            ZonedDateTime businessCloseEST = ZonedDateTime.of(startDateTimeEST.toLocalDate(), LocalTime.of(22, 00), ZoneId.of("America/New_York"));

            if(startDateTimeEST.isBefore(businessOpenEST) || startDateTimeEST.isAfter(businessCloseEST)){
                isGood = false;
                alert.addMessage("Appointment Must Start During Business Hours");
            }
            if(endDateTimeEST.isBefore(businessOpenEST) || endDateTimeEST.isAfter(businessCloseEST)){
                isGood = false;
                alert.addMessage("Appointment Must End During Business Hours");
            }
            if(endDateTimeEST.isBefore(startDateTimeEST)){
                isGood = false;
                alert.addMessage("Appointment Must Begin Before it Ends");
            }
            for (Appointments a : DataList.getAppointmentList(data)
            ) {
                if(cbCustomerID.getSelectionModel().getSelectedIndex() != -1){
                    if(a.getCustomerID() == cbCustomerID.getValue()){
                        if(startDateTimeLocal.toLocalDateTime().isAfter(a.getStart().minusMinutes(1)) && startDateTimeLocal.toLocalDateTime().isBefore(a.getEnd().plusMinutes(1))){
                            isGood = false;
                            alert.addMessage("Appointment Start Time Conflicts with Previously Scheduled Appointment");
                        }
                        if(endDateTimeLocal.toLocalDateTime().isAfter(a.getStart().minusMinutes(1)) && endDateTimeLocal.toLocalDateTime().isBefore(a.getEnd().plusMinutes(1))){
                            isGood = false;
                            alert.addMessage("Appointment End Time Conflicts with Previously Scheduled Appointment");
                        }
                    }
                }
            }
        }
        alert.show(isGood);
        return isGood;
    }
}
