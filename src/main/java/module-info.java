module com.example.albaleh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.albaleh to javafx.fxml;
    exports com.example.albaleh;

}