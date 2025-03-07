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


            Scene scene = new Scene(root);
            stage.setTitle("Kitaverwaltung");

            // Set the application icon
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/kitaverwaltung/images/app-icon.png")));


            // Link the CSS file
            scene.getStylesheets().add(getClass().getResource("/com/example/kitaverwaltung/css/style.css").toExternalForm());


            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double screenWidth = screenBounds.getWidth();
            double screenHeight = screenBounds.getHeight();
            double windowWidth = 800;
            double windowHeight = 600;
            double xPosition = (screenWidth - windowWidth) / 2;
            double yPosition = (screenHeight - windowHeight) / 2;

            stage.setScene(scene);
            stage.setX(xPosition);
            stage.setY(yPosition);
            stage.setMinWidth(800);
            stage.setMinHeight(600);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading login.fxml. Ensure the file is in the correct folder!");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}