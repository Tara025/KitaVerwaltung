package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Erzieher;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;


    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        Erzieher erzieher = ErzieherDAO.getErzieherByEmailAndPassword(email, password);
        Verwalter verwalter = VerwalterDAO.getVerwalterByEmailAndPassword(email, password);

        if (erzieher != null) {
            DashboardController.setCurrentUser(erzieher);
            loadDashboard();
        } else if (verwalter != null) {
            DashboardController.setCurrentUser(verwalter);
            loadDashboard();
        } else {
            showErrorDialog("Login Failed", "Invalid email or password.");
        }
    }

    @FXML
    private void handleCancel() {
        emailField.clear();
        passwordField.clear();
    }

    private void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/dashboard.fxml"));
            Pane pane = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.getScene().setRoot(pane);
            stage.setTitle("Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}