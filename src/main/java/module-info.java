module com.example.albaleh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.albaleh to javafx.fxml;
    exports com.example.albaleh;
}