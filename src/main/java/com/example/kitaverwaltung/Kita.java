package com.example.kitaverwaltung;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Kita extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Kita.class.getResource("kita.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Kita!");
        stage.setScene(scene);

        // Berechnung der Bildschirmgröße
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

        // Berechnung der Fenstergröße
        double windowWidth = 800;  // Die Breite des Fensters
        double windowHeight = 600; // Die Höhe des Fensters

        // Berechnung der Position, um das Fenster zu zentrieren
        double xPosition = (screenWidth - windowWidth) / 2;
        double yPosition = (screenHeight - windowHeight) / 2;

        // Optional: Fenster maximieren und Größenbegrenzung setzen
        // stage.setMaximized(true);  // Maximiert das Fenster
        stage.setScene(scene);
        stage.setX(xPosition);
        stage.setY(yPosition);

        stage.show();
    }

    public static void main(String[] args) {

        launch(args);

        VerwalterDAO.getVerwalter();
    }
}