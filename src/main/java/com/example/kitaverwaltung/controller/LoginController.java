package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.model.Erzieher;
import com.example.kitaverwaltung.model.Verwalter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
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
    private TextField passwordTextField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    public void initialize() {
        // Initially, the TextField should be hidden
        passwordTextField.setVisible(false);
        passwordTextField.managedProperty().bind(passwordTextField.visibleProperty());
        passwordField.managedProperty().bind(passwordField.visibleProperty());

        // Bind the text properties so they stay in sync
        passwordTextField.textProperty().bindBidirectional(passwordField.textProperty());

        // Add a listener to the CheckBox to toggle visibility
        showPasswordCheckBox.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                passwordTextField.setVisible(true);
                passwordField.setVisible(false);
            } else {
                passwordTextField.setVisible(false);
                passwordField.setVisible(true);
            }
        });
    }


    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        Erzieher erzieher = ErzieherDAO.getErzieherByEmailAndPassword(email, password);
        Verwalter verwalter = VerwalterDAO.getVerwalterByEmailAndPassword(email, password);

        if (erzieher != null) {
            DashboardController.setCurrentUser(erzieher);
            loadDashboard(erzieher.getVorname());
        } else if (verwalter != null) {
            // 🔹 Prüfe, ob der Verwalter als gelöscht markiert ist
            if (verwalter.isDeleted()) {
                showErrorDialog("Fehlende Berechtigung", "Fehlende Berechtigung für Login.");
            } else {
                DashboardController.setCurrentUser(verwalter);
                loadDashboard(verwalter.getVorname());
            }
        } else {
            showErrorDialog("Login Fehlgeschlagen", "Ungültige E-Mail oder Passwort.");
        }
    }

    private void loadDashboard(String userName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/dashboard.fxml"));
            Pane pane = loader.load();
            DashboardController dashboardController = loader.getController();
            dashboardController.setWelcomeMessage(userName);

            // Holen der aktuellen Stage
            Stage stage = (Stage) emailField.getScene().getWindow();

            // Setzen der Startgröße für das Dashboard-Fenster
            stage.setWidth(1500);
            stage.setHeight(800);
            stage.centerOnScreen(); // Fenster zentrieren


            // Setzen der Szene
            stage.getScene().setRoot(pane);
            stage.setTitle("Kitaverwaltung Software");


        } catch (IOException e) {
            e.printStackTrace();
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

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
