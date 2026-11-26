package com.mycompany.welcomecard.horsestablesystem.controller;

import com.mycompany.welcomecard.horsestablesystem.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    @FXML
    private void login() {

        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Validate empty fields
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter username and password.");
            return;
        }

        // Validate login credentials
        if (username.equals("admin") && password.equals("1234")) {

            errorLabel.setText("");

            try {
                App.setRoot("dashboard");

            } catch (IOException e) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Unable to open the dashboard.");
                alert.showAndWait();

                e.printStackTrace();
            }

        } else {
            errorLabel.setText("Invalid username or password.");
        }
    }
}