module com.example.assignment4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment4 to javafx.fxml;
    exports com.example.assignment4;
    exports com.example.assignment4.Command;
    opens com.example.assignment4.Command to javafx.fxml;
}