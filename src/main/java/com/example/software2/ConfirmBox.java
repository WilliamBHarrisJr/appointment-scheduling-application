package com.example.software2;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class ConfirmBox {
    /**
     * String of messages to be displayed in the confirmation alert.
     */
    private String messages = new String();

    /**
     * Title of confirmation alert.
     */
    private String title;

    /**
     * Header of confirmation alert.
     */
    private String header;

    /**
     * Constructor of ConfirmBox.
     * @param title
     * @param header
     */
    public ConfirmBox(String title, String header){
        this.title = title;
        this.header = header;
    }

    /**
     * Appends message to string of messages to be displayed in the confirmation alert.
     * @param message
     */
    public void addMessage(String message){
        messages = messages + "\n\n" + message;
    }

    /**
     * Displays confirmation alert.
     * @return Returns boolean value of true if user clicks ok button.
     */
    public boolean show(){
        boolean isOk = false;
        addMessage("\n\n");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(messages);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            isOk = true;
        }
        return isOk;
    }
}