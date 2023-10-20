module com.example.software2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.software2 to javafx.fxml;
    exports com.example.software2;
}