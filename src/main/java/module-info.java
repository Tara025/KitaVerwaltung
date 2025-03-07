module com.example.kitaverwaltung {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.net.http;


    opens com.example.kitaverwaltung.controller to javafx.fxml;
    opens com.example.kitaverwaltung.model to com.google.gson;
    exports com.example.kitaverwaltung;
    exports com.example.kitaverwaltung.db;
    opens com.example.kitaverwaltung.db to javafx.fxml;


    exports com.example.kitaverwaltung.model;  // Stelle sicher, dass das Modul den Package exportiert

}