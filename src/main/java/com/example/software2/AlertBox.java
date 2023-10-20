package com.example.software2;

import javafx.scene.control.Alert;

public class AlertBox {
    /**
     * String of messages to be displayed in alert box.
     */
    private String messages = new String();

    /**
     * Title of alert box.
     */
    private String title;

    /**
     * Alert box header.
     */
    private String header;

    /**
     * Alert box constructor.
     * @param title
     * @param header
     */
    public AlertBox(String title, String header){
        this.title = title;
        this.header = header;
    }

    /**
     * Appends message to string of messages to be displayed in the alert box.
     * @param message
     */
    public void addMessage(String message){
        messages = messages + "\n\n" + message;
    }

    /**
     * Displays the alert box if boolean value of false is passed in.
     * @param isGood
     */
    public void show(Boolean isGood){
        if(!isGood){
            addMessage("\n\n");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(messages);
            alert.showAndWait();
        }
    }
}
