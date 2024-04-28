module com.example.paszport {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.paszport to javafx.fxml;
    exports com.example.paszport;
}