package com.mycompany.welcomecard.horsestablesystem.controller;

import com.mycompany.welcomecard.horsestablesystem.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label horseCountLabel;

    @FXML
    private Label ownerCountLabel;

    @FXML
    private Label stallCountLabel;

    @FXML
    private Label freeStallLabel;

    @FXML
    private ListView<String> recentList;

    @FXML
    private void initialize() {
        welcomeLabel.setText("Welcome back!");

        // Statistics will be connected to actual data later
        horseCountLabel.setText("—");
        ownerCountLabel.setText("—");
        stallCountLabel.setText("—");
        freeStallLabel.setText("—");
    }

    @FXML
    private void goDashboard() {
        navigateTo("dashboard");
    }

    @FXML
    private void goHorses() {
        navigateTo("horses");
    }

    @FXML
    private void goOwners() {
        navigateTo("owners");
    }

    @FXML
    private void goStalls() {
        navigateTo("stalls");
    }

    @FXML
    private void goReports() {
        navigateTo("reports");
    }

    @FXML
    private void logout() {
        navigateTo("login");
    }

    // Shared navigation method
    private void navigateTo(String page) {
        try {
            App.setRoot(page);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to open " + page + ".");
            alert.showAndWait();

            e.printStackTrace();
        }
    }
}