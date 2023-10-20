package com.example.software2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    /**
     * Starts program and goes to login screen.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login-view.fxml"));
        Parent parent = loader.load();

        LoginController controller = loader.getController();
        controller.isFrench();

        Scene scene = new Scene(parent);
        Image icon = new Image(getClass().getResource("/icons/booking.png").openStream());
        stage.getIcons().add(icon);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method.
     * @param args
     */
    public static void main(String[] args) {launch();}
}