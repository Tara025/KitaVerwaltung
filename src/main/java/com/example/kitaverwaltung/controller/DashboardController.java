package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.model.Erzieher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardController {

    @FXML private Button btnDashboard;
    @FXML private Button btnVerwalter;
    @FXML private Button btnErzieher;
    @FXML private Button btnKinder;
    @FXML private Button btnStandorte;
    @FXML private Button btnGruppe;
    @FXML private Button btnLogout;


    @FXML private Pane mainContent;

    private static Object currentUser;

    public static void setCurrentUser(Object user) {
        currentUser = user;
    }

    @FXML
    public void initialize() {
        clearMainContent();

        if (currentUser != null) {
            if (currentUser instanceof Erzieher) {
                btnVerwalter.setDisable(true);
                btnErzieher.setDisable(true);
                btnStandorte.setDisable(true);
            }
        }

        btnVerwalter.setOnAction(e -> loadVerwalterTable());
        btnErzieher.setOnAction(e -> loadErzieherTable());
        btnKinder.setOnAction(e -> loadKinderTable());
        btnStandorte.setOnAction(e -> loadStandorteTable());
        btnGruppe.setOnAction(e -> loadGruppeTable());
        btnLogout.setOnAction(e -> handleLogout());
    }

    @FXML
    private void handleDashboardClick() {
        //loadFXML("/com/example/kitaverwaltung/dashboard.fxml");
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/login.fxml"));
            Pane pane = loader.load();
            Stage stage = (Stage) mainContent.getScene().getWindow();
            stage.getScene().setRoot(pane);
            stage.setTitle("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void clearMainContent() {
        mainContent.getChildren().clear();
    }

    private void loadFXML(String fxmlFile) {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadVerwalterTable() {
        loadFXML("/com/example/kitaverwaltung/verwalter.fxml");
    }

    @FXML
    private void loadErzieherTable() {
        loadFXML("/com/example/kitaverwaltung/erzieher.fxml");
    }

    @FXML
    private void loadKinderTable() {
        loadFXML("/com/example/kitaverwaltung/kind.fxml");
    }

    @FXML
    private void loadStandorteTable() {
        loadFXML("/com/example/kitaverwaltung/standort.fxml");
    }

    @FXML
    private void loadGruppeTable() {
        loadFXML("/com/example/kitaverwaltung/gruppe.fxml");
    }
}