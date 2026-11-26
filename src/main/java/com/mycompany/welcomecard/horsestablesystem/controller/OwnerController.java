package com.mycompany.welcomecard.horsestablesystem.controller;

import com.mycompany.welcomecard.horsestablesystem.App;
import com.mycompany.welcomecard.horsestablesystem.model.Owner;

import java.io.IOException;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class OwnerController {

    // Form components
    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextArea addressArea;

    @FXML
    private TextField searchField;

    @FXML
    private Label statusLabel;

    // Table components
    @FXML
    private TableView<Owner> ownerTable;

    @FXML
    private TableColumn<Owner, Integer> idCol;

    @FXML
    private TableColumn<Owner, String> nameCol;

    @FXML
    private TableColumn<Owner, String> phoneCol;

    @FXML
    private TableColumn<Owner, String> emailCol;

    @FXML
    private TableColumn<Owner, String> addressCol;

    // Temporary list for CRUD operations
    private final ObservableList<Owner> owners =
            FXCollections.observableArrayList();

    private int nextID = 1;


    // Initialize the interface
    @FXML
    private void initialize() {

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("ownerId"));

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        phoneCol.setCellValueFactory(
                new PropertyValueFactory<>("phone"));

        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));

        addressCol.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        // Search functionality
        FilteredList<Owner> filteredOwners =
                new FilteredList<>(owners, owner -> true);

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

            String keyword = newValue.trim()
                    .toLowerCase(Locale.ROOT);

            filteredOwners.setPredicate(owner ->
                    owner.getName().toLowerCase(Locale.ROOT)
                            .contains(keyword)
                    ||
                    owner.getPhone().contains(keyword));
        });

        SortedList<Owner> sortedOwners =
                new SortedList<>(filteredOwners);

        sortedOwners.comparatorProperty()
                .bind(ownerTable.comparatorProperty());

        ownerTable.setItems(sortedOwners);

        updateStatus();
    }


    // CREATE: Add an owner
    @FXML
    private void handleAdd() {

        if (!validateInput()) {
            return;
        }

        Owner owner = new Owner(
                nextID++,
                nameField.getText().trim(),
                phoneField.getText().trim(),
                emailField.getText().trim(),
                addressArea.getText().trim()
        );

        owners.add(owner);

        handleClear();

        showAlert(Alert.AlertType.INFORMATION,
                "Success", "Owner added successfully.");
    }


    // Mouse Event: Select an owner
    @FXML
    private void handleRowClick() {

        Owner selected =
                ownerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        nameField.setText(selected.getName());
        phoneField.setText(selected.getPhone());
        emailField.setText(selected.getEmail());
        addressArea.setText(selected.getAddress());
    }


    // UPDATE: Modify an owner
    @FXML
    private void handleUpdate() {

        Owner selected =
                ownerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Warning", "Please select an owner first.");

            return;
        }

        if (!validateInput()) {
            return;
        }

        Owner updatedOwner = new Owner(
                selected.getOwnerId(),
                nameField.getText().trim(),
                phoneField.getText().trim(),
                emailField.getText().trim(),
                addressArea.getText().trim()
        );

        int index = owners.indexOf(selected);

        owners.set(index, updatedOwner);

        handleClear();

        showAlert(Alert.AlertType.INFORMATION,
                "Success", "Owner updated successfully.");
    }


    // DELETE: Remove an owner
    @FXML
    private void handleDelete() {

        Owner selected =
                ownerTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Warning", "Please select an owner first.");

            return;
        }

        Alert confirmation =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText(null);

        confirmation.setContentText(
                "Are you sure you want to delete "
                + selected.getName() + "?");

        if (confirmation.showAndWait()
                .orElse(ButtonType.CANCEL) == ButtonType.OK) {

            owners.remove(selected);

            handleClear();

            showAlert(Alert.AlertType.INFORMATION,
                    "Success", "Owner deleted successfully.");
        }
    }


    // Clear the form
    @FXML
    private void handleClear() {

        nameField.clear();
        phoneField.clear();
        emailField.clear();
        addressArea.clear();

        ownerTable.getSelectionModel().clearSelection();

        updateStatus();
    }


    // Input validation
    private boolean validateInput() {

        if (nameField.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter the owner name.");

            return false;
        }

        if (!phoneField.getText().trim()
                .matches("05[0-9]{8}")) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter a valid phone number "
                    + "starting with 05.");

            return false;
        }

        if (!emailField.getText().trim()
                .matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter a valid email address.");

            return false;
        }

        if (addressArea.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter the address.");

            return false;
        }

        return true;
    }


    // Update the status label
    private void updateStatus() {

        statusLabel.setText(
                "Total owners: " + owners.size());
    }


    // Reusable alert method
    private void showAlert(Alert.AlertType type,
                           String title, String message) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    // Navigation methods
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
        // Already on the Owners page
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

    private void navigateTo(String page) {

        try {

            App.setRoot(page);

        } catch (IOException e) {

            showAlert(Alert.AlertType.ERROR,
                    "Navigation Error",
                    "Unable to open " + page + ".");

            e.printStackTrace();
        }
    }
}