module com.example.teonta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.teonta to javafx.fxml;
    exports com.example.teonta;
}