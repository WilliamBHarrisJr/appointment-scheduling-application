package com.example.software2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    Data data = Data.getInstance();
    DBConnection connection = new DBConnection();

    /**
     * GUI tab pane.
     */
    @FXML
    TabPane tabPane = new TabPane();

    /**
     * GUI table view.
     */
    @FXML
    TableView<Customers> tableCustomer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, Integer> colCustomerID_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colName_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colPhone_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colAddress_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colPostalCode_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, LocalDate> colCreateDate_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colCreatedBy_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, LocalDate> colLastUpdate_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colLastUpdatedBy_Customer;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colDivisionName_Customer;

    /**
     * GUI table view.
     */
    @FXML
    TableView<Appointments> tableAppointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colAppointmentID_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colTitle_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colDescription_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colLocation_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colType_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, LocalTime> colStart_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, LocalTime> colEnd_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colCustomerID_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colUserID_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colContactID_Appointment;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, LocalDate> colDate_Appointment;

    @FXML
    TableColumn<Appointments, LocalDate> colEndDate_Appointment;

    /**
     * GUI table view.
     */
    @FXML TableView<Appointments> tableReportsAppointments = new TableView<Appointments>();

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colAppointmentID_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colTitle_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colDescription_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colLocation_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, String> colType_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, LocalTime> colStart_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, LocalTime> colEnd_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colCustomerID_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colUserID_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, Integer> colContactID_AppointmentReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Appointments, LocalDate> colDate_AppointmentReports;

    @FXML
    TableColumn<Appointments, LocalDate> colEndDate_AppointmentReports;

    /**
     * GUI table view.
     */
    @FXML TableView<Customers> tableReportsCustomers = new TableView<Customers>();

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, Integer> colCustomerID_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colName_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colPhone_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colAddress_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colPostalCode_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, LocalDate> colCreateDate_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colCreatedBy_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, LocalDate> colLastUpdate_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colLastUpdatedBy_CustomerReports;

    /**
     * GUI table column.
     */
    @FXML
    TableColumn<Customers, String> colDivisionName_CustomerReports;

    /**
     * GUI button.
     */
    @FXML
    Button btnAddAppointment;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbReportType;

    /**
     * GUI combo-box.
     */
    @FXML private ComboBox<String> cbCriteria;

    /**
     * GUI label.
     */
    @FXML private Label lbCriteria;

    /**
     * Initializes tables.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCustomers();
        initAppointments();
        showCustomers();
        showAppointments();
        initReports();
        cbReportType.setItems(DataList.getReportTypeList());
    }

    /**
     * Goes back to log in screen.
     * @param event
     * @throws IOException
     */
    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login-View.fxml"));
        Parent parent = loader.load();

        LoginController controller = loader.getController();
        controller.data.setData(this.data);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
        window.getIcons().add(icon);
        window.setTitle("Login");
        window.setScene(scene);
        window.show();
        controller.isFrench();
    }

    /**
     * Goes to add customer screen.
     * @param event
     * @throws IOException
     */
    public void addCustomerScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addCustomer-View.fxml"));
        Parent parent = loader.load();

        AddCustomerController controller = loader.getController();
        controller.data.setData(this.data);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
        window.getIcons().add(icon);
        window.setTitle("Add Customer");
        window.setScene(scene);
        window.show();
    }

    /**
     * Goes to add appointment screen.
     * @param event
     * @throws IOException
     */
    public void addAppointmentScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addAppointment-view.fxml"));
        Parent parent = loader.load();

        AddAppointmentController controller = loader.getController();
        controller.data.setData(this.data);

        Scene scene = new Scene(parent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
        window.getIcons().add(icon);
        window.setTitle("Add Appointment");
        window.setScene(scene);
        window.show();
    }

    /**
     * Passes selected customer to and goes to modify customer screen.
     * @param event
     * @throws IOException
     */
    public void modifyCustomerScreen(ActionEvent event) throws IOException {
        if(tableCustomer.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyCustomer-view.fxml"));
            Parent parent = loader.load();

            ModifyCustomerController controller = loader.getController();
            controller.data.setData(this.data);
            controller.initData(tableCustomer.getSelectionModel().getSelectedItem());

            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
            window.getIcons().add(icon);
            window.setTitle("Modify Customer");
            window.setScene(scene);
            window.show();
        }
    }

    /**
     * Passes selected appointment to and goes to modify appointment screen.
     * @param event
     * @throws IOException
     */
    public void modifyAppointmentScreen(ActionEvent event) throws IOException {
        if(tableAppointment.getSelectionModel().getSelectedItem() != null){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("modifyAppointment-view.fxml"));
            Parent parent = loader.load();

            ModifyAppointmentController controller = loader.getController();
            controller.data.setData(this.data);
            controller.initData(tableAppointment.getSelectionModel().getSelectedItem());

            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
            window.getIcons().add(icon);
            window.setTitle("Modify Appointment");
            window.setScene(scene);
            window.show();
        }
    }

    /**
     * Initializes customer table.
     */
    public void initCustomers(){
        colCustomerID_Customer.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("iD"));
        colName_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        colPhone_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));
        colAddress_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        colPostalCode_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        colCreateDate_Customer.setCellValueFactory(new PropertyValueFactory<Customers, LocalDate>("createDate"));
        colCreatedBy_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("createdBy"));
        colLastUpdate_Customer.setCellValueFactory(new PropertyValueFactory<Customers, LocalDate>("lastUpdate"));
        colLastUpdatedBy_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("lastUpdatedBy"));
        colDivisionName_Customer.setCellValueFactory(new PropertyValueFactory<Customers, String>("division"));
    }

    /**
     * Populates customer table.
     */
    public void showCustomers(){
        ObservableList<Customers> list = DataList.getCustomerList(data);
        tableCustomer.setItems(list);
    }

    /**
     * Creates SQL statement to remove selected customer from database.
     */
    public void removeCustomer(){
        if(tableCustomer.getSelectionModel().getSelectedItem() != null){
            Customers selectedCustomer = tableCustomer.getSelectionModel().getSelectedItem();
            if(customerHasAppointmentCheck(selectedCustomer)){
                ConfirmBox confirm = new ConfirmBox("Remove Customer", "Remove Customer Confirmation");
                confirm.addMessage("Are you sure that you want to remove the customer?");
                if(confirm.show()){
                    String query = "DELETE FROM customers WHERE Customer_ID =" + selectedCustomer.getID();
                    connection.executeQuery(query);
                    showCustomers();
                }
            }
        }
    }

    /**
     * Checks to see if customer has any scheduled appointments.
     * @param customer
     * @return
     */
    public boolean customerHasAppointmentCheck(Customers customer){
        boolean isGood = true;
        AlertBox alert = new AlertBox("Remove Customer", "Error Removing Customer");
        ObservableList<Appointments> appointmentList = DataList.getAppointmentList(data);
        for (Appointments a : appointmentList
        ) {
            if(a.getCustomerID() == customer.getID()){
                isGood = false;
            }
        }
        if(!isGood){
            alert.addMessage("Customer Cannot Be Removed Because They Have a Scheduled Appointment");
        }
        alert.show(isGood);
        return isGood;
    }

    /**
     * Creates SQL query to remove appointment from database.
     */
    public void cancelAppointment(){
        if(tableAppointment.getSelectionModel().getSelectedItem() != null){
            Appointments selectedAppointment = tableAppointment.getSelectionModel().getSelectedItem();
            ConfirmBox confirm = new ConfirmBox("Cancel Appointment", "Cancel Appointment Confirmation");
            confirm.addMessage("Are you sure you want to cancel the " + selectedAppointment.getType() +
                    " appointment with ID " + selectedAppointment.getAppointmentID() + "?");
            if(confirm.show()){
                String query = "DELETE FROM appointments WHERE Appointment_ID =" + selectedAppointment.getAppointmentID();
                connection.executeQuery(query);
                showAppointments();
            }
         }
    }

    /**
     * Initializes appointment table.
     */
    public void initAppointments(){
        colAppointmentID_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        colTitle_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        colDescription_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        colLocation_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        colType_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        colDate_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDate>("date"));
        colStart_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, LocalTime>("startTime"));
        colEndDate_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDate>("endDate"));
        colEnd_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, LocalTime>("endTime"));
        colCustomerID_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
        colUserID_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));
        colContactID_Appointment.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("contactID"));
    }

    /**
     * Populates appointment table.
     */
    public void showAppointments(){
        tableAppointment.setItems(DataList.getAppointmentList(data));
    }


    boolean isClicked = false;

    /**
     * Sets up report table.
     */
    public void setReports(){
        tableReportsAppointments.getItems().clear();
        tableReportsCustomers.getItems().clear();
        cbCriteria.getItems().removeAll();
        cbCriteria.setVisible(true);
        lbCriteria.setVisible(true);

        if(cbReportType.getValue().equals("Contact Schedule")){
            tableReportsCustomers.setVisible(false);
            tableReportsAppointments.setVisible(true);
            isClicked = false;
            cbCriteria.setItems(DataList.getContactIDStringList(data));
            lbCriteria.setText("Contact");
            isClicked = true;

        } else if (cbReportType.getValue().equals("Customers by Type")) {
            tableReportsAppointments.setVisible(false);
            tableReportsCustomers.setVisible(true);
            isClicked = false;
            lbCriteria.setText("Type");
            cbCriteria.setItems(DataList.getTypeList(data));
            isClicked = true;

        } else if (cbReportType.getValue().equals("Customers by Month")) {
            tableReportsAppointments.setVisible(false);
            tableReportsCustomers.setVisible(true);
            isClicked = false;
            lbCriteria.setText("Month");
            cbCriteria.setItems(DataList.getMonthList());
            isClicked = true;

        } else if (cbReportType.getValue().equals("Customers by Country")) {
            tableReportsAppointments.setVisible(false);
            tableReportsCustomers.setVisible(true);
            isClicked = false;
            lbCriteria.setText("Country");
            cbCriteria.setItems(DataList.getCountryNameList(data));
            isClicked = true;
        }

    }

    /**
     * Initializes report table.
     */
    public void initReports(){
        colAppointmentID_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        colTitle_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        colDescription_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        colLocation_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        colType_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        colDate_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDate>("date"));
        colEndDate_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, LocalDate>("endDate"));
        colStart_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, LocalTime>("startTime"));
        colEnd_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, LocalTime>("endTime"));
        colCustomerID_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));
        colUserID_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("userID"));
        colContactID_AppointmentReports.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("contactID"));

        colCustomerID_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("iD"));
        colName_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        colPhone_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));
        colAddress_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        colPostalCode_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        colCreateDate_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, LocalDate>("createDate"));
        colCreatedBy_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("createdBy"));
        colLastUpdate_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, LocalDate>("lastUpdate"));
        colLastUpdatedBy_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("lastUpdatedBy"));
        colDivisionName_CustomerReports.setCellValueFactory(new PropertyValueFactory<Customers, String>("division"));
    }

    /**
     * Populates report table.
     */
    public void showReports(){

        if(cbReportType.getValue().equals("Contact Schedule")){
            if(isClicked){
                tableReportsAppointments.setItems(DataList.getAppointmentListByContactID(data, Integer.parseInt(cbCriteria.getValue())));
            }

        } else if (cbReportType.getValue().equals("Customers by Type")) {
            if(isClicked){
                tableReportsCustomers.setItems(DataList.getCustomerListByType(data, cbCriteria.getValue()));
            }

        } else if (cbReportType.getValue().equals("Customers by Month")) {
            if(isClicked){
                tableReportsCustomers.setItems(DataList.getCustomerListByMonth(data, cbCriteria.getValue()));
            }

        } else if (cbReportType.getValue().equals("Customers by Country")) {
            if(isClicked){
                tableReportsCustomers.setItems(DataList.getCustomerListByCountry(data, cbCriteria.getValue()));
            }
        }
    }

    /**
     * Populates appointment table with appointments for current month.
     */
    public void rbMonthSelected(){
       tableAppointment.setItems(DataList.getAppointmentListForCurrentMonth(data));
    }

    /**
     * Populates appointment table with all appointments.
     */
    public void rbAllSelected(){
        tableAppointment.setItems(DataList.getAppointmentList(data));
    }

    /**
     * Populates appointment table with appointments for current week.
     */
    public void rbWeekSelected(){
        tableAppointment.setItems(DataList.getAppointmentListForCurrentWeek(data));
    }

    /**
     * Checks to see if there is an appointment in the next 15 minutes and displays the appropriate a;ert box message.
     */
    public void upcomingAppointmentCheck(){
        AlertBox alert = new AlertBox("Upcoming Appointments", "Upcoming Appointments");
        boolean isAppointment = false;
        for (Appointments a : DataList.getAppointmentList(data)
             ) {
            if(a.getStart().isBefore(LocalDateTime.now().plusMinutes(15)) && a.getStart().isAfter(LocalDateTime.now())){
                alert.addMessage("Appointment with ID " + a.getAppointmentID() + " is coming up on " +
                        a.getStart().toLocalDate() + " at " + a.getStart().toLocalTime());
                isAppointment = true;
            }
        }
        if(!isAppointment){
            alert.addMessage("There are no upcoming appointments");
        }
        alert.show(false);
    }

}
