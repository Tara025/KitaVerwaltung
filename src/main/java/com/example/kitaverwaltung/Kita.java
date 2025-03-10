package com.example.kitaverwaltung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class Kita extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/login.fxml"));
            Pane root = loader.load();


            Scene scene = new Scene(root, 800, 600); // Feste Startgröße für Login
            stage.setTitle("Kitaverwaltung");

            // Anwendungssymbol
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/kitaverwaltung/images/app-icon.png")));


            // Link the CSS file
            scene.getStylesheets().add(getClass().getResource("/com/example/kitaverwaltung/css/style.css").toExternalForm());

            // Fenster zentrieren
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - 800) / 2);
            stage.setY((screenBounds.getHeight() - 600) / 2);

            // Minimale Fenstergröße
            stage.setMinWidth(800);
            stage.setMinHeight(600);

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Fehler beim Laden von login.fxml. Überprüfe den Dateipfad!");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}