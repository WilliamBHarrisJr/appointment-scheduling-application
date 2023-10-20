package com.example.software2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {
    Data data = Data.getInstance();
    DBConnection connection = new DBConnection();

    /**
     * GUI text field.
     */
    @FXML private  TextField tfCustomerID;

    /**
     * GUI text field.
     */
    @FXML private TextField tfName;

    /**
     * GUI text field.
     */
    @FXML private  TextField tfAddress;

    /**
     * GUI text field.
     */
    @FXML private  TextField tfPostalCode;

    /**
     * GUI text field.
     */
    @FXML private  TextField tfPhone;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbCountry;

    /**
     * GUI combo-box.
     */
    @FXML private  ComboBox<String> cbState;

    /**
     * Checks inputs for errors, inserts customer into database, sends data to and goes to main screen.
     * @param event
     * @throws IOException
     */
    public void save(ActionEvent event) throws IOException {
        if(inputErrorCheck()){
            insertCustomer();
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
     * Creates SQL query and inserts new customer into database.
     */
    public void insertCustomer(){
        java.sql.Date date = java.sql.Date.valueOf(localToUTC(LocalDateTime.now()).toLocalDate());
        String query = "INSERT INTO customers VALUES ("+tfCustomerID.getText()+",'"+tfName.getText()+"','"+tfAddress.getText()+
                "','"+tfPostalCode.getText()+"','"+tfPhone.getText()+"','"+date+"','"+ data.getUser()+"','"+date+"','"+ data.getUser()+
                "',"+DataList.getDivisionID(data, cbState.getValue())+");";
        connection.executeQuery(query);
    }

    /**
     * Sets division combo-box items according to which country is selected.
     */
    public void setDivisionItems(){
        cbState.setItems(DataList.getDivisionNameList(data, cbCountry.getValue()));
    }

    /**
     * Initializes Customer ID text-field and Country combo-box.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfCustomerID.setText(Integer.toString(DataList.generateCustomerID(data)));
        cbCountry.setItems(DataList.getCountryNameList(data));
    }

    /**
     * Checks all inputs for errors and conflicts.
     * @return
     */
    public boolean inputErrorCheck(){
        boolean isGood = true;
        AlertBox alert = new AlertBox("Customer Error", "Error Adding Customer");

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
