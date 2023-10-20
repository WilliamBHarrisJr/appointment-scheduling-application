package com.example.software2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ModifyCustomerController {
    Data data = Data.getInstance();
    DBConnection connection = new DBConnection();
    /**
     * GUI text field.
     */
    @FXML private TextField tfID;

    /**
     * GUI text field.
     */
    @FXML private TextField tfName;

    /**
     * GUI text field.
     */
    @FXML private TextField tfAddress;

    /**
     * GUI text field.
     */
    @FXML private TextField tfPostalCode;

    /**
     * GUI text field.
     */
    @FXML private TextField tfPhone;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbCountry;

    /**
     * GUI combo-box.
     */
    @FXML private  ComboBox<String> cbState;

    /**
     * If input data is valid, then customer is modified and data is passed to and user is returned to the main screen.
     * @param event
     * @throws IOException
     */
    public void save(ActionEvent event) throws IOException {
        if(inputErrorCheck()){
            modifyCustomer();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainScreen-view.fxml"));
            Parent parent = loader.load();

            MainScreenController controller = loader.getController();
            controller.data.setData(this.data);
            controller.tabPane.getSelectionModel().select(1);

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
     * Returns to main screen without saving changes.
     * @param event
     * @throws IOException
     */
    public void cancel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen-view.fxml"));
        Parent parent = loader.load();

        MainScreenController controller = loader.getController();
        controller.data.setData(this.data);
        controller.tabPane.getSelectionModel().select(1);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
        window.getIcons().add(icon);
        window.setTitle("Appointment Scheduler");
        window.setScene(scene);
        window.show();
    }

    /**
     * Initializes GUI elements.
     * @param selectedCustomer
     */
    public void initData(Customers selectedCustomer){
        tfID.setText(Integer.toString(selectedCustomer.getID()));
        tfName.setText(selectedCustomer.getName());
        tfAddress.setText(selectedCustomer.getAddress());
        tfPostalCode.setText(selectedCustomer.getPostalCode());
        tfPhone.setText(selectedCustomer.getPhone());
        cbCountry.setItems(DataList.getCountryNameList(data));
        cbCountry.setValue(DataList.getCountryName(data, selectedCustomer.getDivisionId()));
        cbState.setItems(DataList.getDivisionNameList(data, selectedCustomer.getCountry()));
        cbState.setValue(selectedCustomer.getDivision());

    }

    /**
     * Sets division combo-box items according to which country is selected.
     */
    public void setDivisionItems(){
        cbState.setItems(DataList.getDivisionNameList(data, cbCountry.getValue()));
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
     * Creates SQL query and updates customer in database.
     */
    public void modifyCustomer(){
        java.sql.Date date = java.sql.Date.valueOf(localToUTC(LocalDateTime.now()).toLocalDate());
        String query = "UPDATE customers SET Customer_Name = '"+tfName.getText()+"', Address = '"+tfAddress.getText()+
                "', Postal_Code = '"+tfPostalCode.getText()+"', Phone = '"+tfPhone.getText()+"', Last_Update = '"+date+
                "', Last_Updated_By = '"+data.getUser()+"', Division_ID = '"+DataList.getDivisionID(data, cbState.getValue())+
                "' WHERE Customer_ID = "+tfID.getText()+";";
        connection.executeQuery(query);
    }

    /**
     * Checks inputs for errors.
     * @return
     */
    public boolean inputErrorCheck(){
        boolean isGood = true;
        AlertBox alert = new AlertBox("Customer Error", "Error Modifying Customer");

        if(tfPhone.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Customer Phone Number is Needed");
        }
        if(tfAddress.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Customer Address is Needed");
        }
        if(tfName.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Customer Name is Needed");
        }
        if(tfPostalCode.getText().isEmpty()){
            isGood = false;
            alert.addMessage("Customer Postal Code is Needed");
        }
        if(cbState.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            alert.addMessage("Customer State is is Needed");
        }
        if(cbCountry.getSelectionModel().getSelectedIndex() == -1){
            isGood = false;
            alert.addMessage("Customer First-Level-Division is Needed");
        }
        alert.show(isGood);

        return isGood;
    }
}
