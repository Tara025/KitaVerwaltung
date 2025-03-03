package com.example.kitaverwaltung;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Kita extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Kita.class.getResource("kita.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Kita!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {



        VerwalterDAO.getVerwalter();
    }
}