package com.example.kitaverwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Kita extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // FXML-Datei laden
            FXMLLoader fxmlLoader = new FXMLLoader(Kita.class.getResource("dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);

            // Fenster-Titel setzen
            stage.setTitle("Kita Verwaltung");

            // Fenstergröße berechnen und zentrieren
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double screenWidth = screenBounds.getWidth();
            double screenHeight = screenBounds.getHeight();
            double windowWidth = 800;
            double windowHeight = 600;
            double xPosition = (screenWidth - windowWidth) / 2;
            double yPosition = (screenHeight - windowHeight) / 2;

            // Szene setzen und Fenster positionieren
            stage.setScene(scene);
            stage.setX(xPosition);
            stage.setY(yPosition);

            // Optional: Fenster maximieren
            // stage.setMaximized(true);

            // Fenster anzeigen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Laden von dashboard.fxml. Stelle sicher, dass die Datei im richtigen Ordner liegt!");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
