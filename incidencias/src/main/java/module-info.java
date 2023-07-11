module com.example.entregable {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    exports com.example.entregable;
    opens com.example.entregable to javafx.fxml;
    exports com.example.entregable.controlador;
    opens com.example.entregable.controlador to javafx.fxml;
    exports com.example.entregable.modelo;
    opens com.example.entregable.modelo to javafx.fxml;

}