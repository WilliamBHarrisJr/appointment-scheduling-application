package com.example.software2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

public class LoginController implements Initializable {

    Data data = Data.getInstance();
    DBConnection connection = new DBConnection();

    /**
     * GUI text field.
     */
    @FXML private TextField tfUser;

    /**
     * GUI text field.
     */
    @FXML private PasswordField pfPassword;

    /**
     * GUI label.
     */
    @FXML private Label lbLoginFailed;

    /**
     * GUI label.
     */
    @FXML private Label lbLocation;

    /**
     * GUI label.
     */
    @FXML private Label lbTimeZone;

    /**
     * GUI label.
     */
    @FXML private Label lbSystemLanguage;

    /**
     * GUI label.
     */
    @FXML private Label lbLanguage;

    /**
     * GUI label.
     */
    @FXML private Label lbUsername;

    /**
     * GUI label.
     */
    @FXML private Label lbPassword;

    /**
     * GUI label.
     */
    @FXML private Label lbLogin;

    /**
     * GUI label.
     */
    @FXML private Button btnSubmit;

    /**
     * GUI label.
     */
    @FXML private Button btnExit;

    /**
     * Closes program.
     * @param event
     */
    public void close(ActionEvent event){
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Changes GUI elements to French if French is the system default language.
     */
    public void isFrench(){
        if(Locale.getDefault().getDisplayLanguage().equals(Locale.getDefault().getDisplayLanguage(Locale.FRENCH))){
            lbLogin.setText("Connexion");
            lbPassword.setText("Mot de passe");
            lbUsername.setText("Nom d'utilisateur");
            lbTimeZone.setText("Fuseau horaire:");
            lbLanguage.setText("Langue:");
            lbLoginFailed.setText("L'identifiant ou le mot de passe est incorrect");
            tfUser.setPromptText("Nom d'utilisateur");
            pfPassword.setPromptText("Mot de passe");
            btnSubmit.setText("Soumettre");
            btnExit.setText("Sortie");
            lbTimeZone.setLayoutX(23);
            lbSystemLanguage.setLayoutX(345);
            lbLogin.setLayoutX(150);
            lbUsername.setLayoutX(45);
            lbPassword.setLayoutX(60);
        }
    }

    /**
     * If username and password is valid, then data is passed to and goes to main screen.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void login(ActionEvent event) throws IOException, SQLException {
        if(isValid()){
            System.out.println("UserID: " + data.getUserID());

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
            controller.upcomingAppointmentCheck();
        }
    }

    /**
     * Creates SQL statement to check if username and password is valid.
     * @return
     * @throws SQLException
     */
    public boolean isValid() throws SQLException {
        boolean isValid;
        String username = tfUser.getText();
        String password = pfPassword.getText();
        User validUser;

        Connection conn = connection.openConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE User_Name = ? AND Password = ?");
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs;
        rs = pst.executeQuery();

        if(rs.next()){
            isValid = true;
            validUser = new User(rs.getInt("User_ID"), rs.getString("User_Name"));
            data.setUser(validUser.getUserName());
            data.setUserID(validUser.getUserID());
            System.out.println("Login successful");
            writeLoginActivity("Login Successful");
        }else{
            isValid = false;
            System.out.println("Login failed");
            writeLoginActivity("Login Failed");
            lbLoginFailed.setVisible(true);
        }

        return isValid;
    }

    /**
     * Initializes GUI elements, time zone and language.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbLocation.setText(TimeZone.getDefault().getID());
        lbSystemLanguage.setText(Locale.getDefault().getDisplayLanguage());
        tfUser.setText("test");
        pfPassword.setText("test");

    }

    /**
     * Appends text to file of login activity.
     * @param isSuccessful
     */
    private void writeLoginActivity(String isSuccessful){
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            String pathString = System.getProperty("user.dir") + "\\" + "login_activity.txt";
            Path path = Path.of(pathString);
            if(Files.exists(path)){
                FileWriter writer = new FileWriter(pathString, true);
                writer.append("\nUsername: " + tfUser.getText() + "     Date: " + dateTime.toLocalDate() + "     Time: " + dateTime.toLocalTime() + "     " + isSuccessful);
                writer.close();
            }
            else if(Files.notExists(path)){
                FileWriter writer = new FileWriter(pathString);
                writer.write("Username: " + tfUser.getText() + "     Date: " + dateTime.toLocalDate() + "     Time: " + dateTime.toLocalTime() + "     " + isSuccessful);
                writer.close();
            }
        }catch (Exception e){

        }
    }
}
