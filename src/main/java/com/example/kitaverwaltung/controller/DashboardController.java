package com.example.kitaverwaltung.controller;

import com.example.kitaverwaltung.dao.VerwalterDAO;
import com.example.kitaverwaltung.dao.ErzieherDAO;
import com.example.kitaverwaltung.dao.KindDAO;
import com.example.kitaverwaltung.dao.StandortDAO;
import com.example.kitaverwaltung.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class DashboardController {

    @FXML private Button btnDashboard;
    @FXML private Button btnVerwalter;
    @FXML private Button btnErzieher;
    @FXML private Button btnKinder;
    @FXML private Button btnStandorte;
    @FXML private Button btnGruppe;

    @FXML private Pane mainContent;

    private ObservableList<Verwalter> verwalterListe = FXCollections.observableArrayList();
    private ObservableList<Erzieher> erzieherListe = FXCollections.observableArrayList();
    private ObservableList<Kind> kinderListe = FXCollections.observableArrayList();
    private ObservableList<Standort> standortListe = FXCollections.observableArrayList();
    private ObservableList<Gruppe> gruppenListe = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        // Zu Beginn keine Tabelle anzeigen (leeren Inhalt setzen)
        clearMainContent();

        // Event-Handler für die Buttons im Dashboard
        btnVerwalter.setOnAction(e -> loadVerwalterTable());
        btnErzieher.setOnAction(e -> loadErzieherTable());
        btnKinder.setOnAction(e -> loadKinderTable());
        btnStandorte.setOnAction(e -> loadStandorteTable());
        btnGruppe.setOnAction(e -> loadGruppeTable());
    }

    @FXML
    public void handleDashboardClick() {
        // Hier kannst du den Code für den Dashboard-Button einfügen
        System.out.println("Dashboard Button geklickt!");
        // Zum Beispiel könntest du das Dashboard anzeigen oder leeren
    }

    @FXML
    private void clearMainContent() {
        mainContent.getChildren().clear();  // Löscht den aktuellen Inhalt
    }

    @FXML
    private void loadVerwalterTable() {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/verwalter.fxml"));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadErzieherTable() {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/erzieher.fxml"));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadKinderTable() {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/kind.fxml"));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadStandorteTable() {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/standort.fxml"));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadGruppeTable() {
        clearMainContent();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/kitaverwaltung/gruppe.fxml"));
            Pane pane = loader.load();
            mainContent.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }}

