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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

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
            // 🔹 Prüfe, ob der Verwalter als gelöscht markiert ist
            if (verwalter.isDeleted()) {
                showErrorDialog("Fehlende Berechtigung", "Fehlende Berechtigung für Login.");
            } else {
                DashboardController.setCurrentUser(verwalter);
                loadDashboard();
            }
        } else {
            showErrorDialog("Login Fehlgeschlagen", "Ungültige E-Mail oder Passwort.");
        }
    }


    @FXML
    private void handleCancel() {
        emailField.clear();
        passwordField.clear();
    }

    @FXML
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLogin();
        }
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