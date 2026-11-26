package com.mycompany.welcomecard.horsestablesystem.controller;

import com.mycompany.welcomecard.horsestablesystem.App;
import com.mycompany.welcomecard.horsestablesystem.model.Horse;

import java.io.IOException;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HorseController {

    // Form components
    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> breedCombo;

    @FXML
    private TextField ageField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private ComboBox<Integer> ownerCombo;

    @FXML
    private ComboBox<Integer> stallCombo;

    @FXML
    private TextField searchField;

    @FXML
    private Label statusLabel;

    // Table components
    @FXML
    private TableView<Horse> horseTable;

    @FXML
    private TableColumn<Horse, Integer> idCol;

    @FXML
    private TableColumn<Horse, String> nameCol;

    @FXML
    private TableColumn<Horse, String> breedCol;

    @FXML
    private TableColumn<Horse, Integer> ageCol;

    @FXML
    private TableColumn<Horse, String> genderCol;

    @FXML
    private TableColumn<Horse, Integer> ownerCol;

    @FXML
    private TableColumn<Horse, Integer> stallCol;

    // Temporary list for CRUD operations
    private final ObservableList<Horse> horses =
            FXCollections.observableArrayList();

    private int nextID = 1;

    // Initialize the interface
    @FXML
    private void initialize() {

        breedCombo.getItems().setAll(
                "Arabian",
                "Thoroughbred",
                "Quarter Horse",
                "Friesian",
                "Andalusian",
                "Other"
        );

        // Owner and stall data will be connected
        // during final integration.

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("horseID"));

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        breedCol.setCellValueFactory(
                new PropertyValueFactory<>("breed"));

        ageCol.setCellValueFactory(
                new PropertyValueFactory<>("age"));

        genderCol.setCellValueFactory(
                new PropertyValueFactory<>("gender"));

        ownerCol.setCellValueFactory(
                new PropertyValueFactory<>("ownerID"));

        stallCol.setCellValueFactory(
                new PropertyValueFactory<>("stallID"));

        // Search functionality
        FilteredList<Horse> filteredHorses =
                new FilteredList<>(horses, horse -> true);

        searchField.textProperty().addListener(
                (observable, oldValue, newValue) -> {

            String keyword = newValue.trim()
                    .toLowerCase(Locale.ROOT);

            filteredHorses.setPredicate(horse ->
                    horse.getName().toLowerCase(Locale.ROOT)
                            .contains(keyword)
                    ||
                    horse.getBreed().toLowerCase(Locale.ROOT)
                            .contains(keyword));
        });

        SortedList<Horse> sortedHorses =
                new SortedList<>(filteredHorses);

        sortedHorses.comparatorProperty()
                .bind(horseTable.comparatorProperty());

        horseTable.setItems(sortedHorses);

        updateStatus();
    }

    // CREATE: Add a horse
    @FXML
    private void handleAdd() {

        if (!validateInput()) {
            return;
        }

        Horse horse = new Horse(
                nextID++,
                nameField.getText().trim(),
                breedCombo.getValue(),
                Integer.parseInt(ageField.getText().trim()),
                getGender(),
                ownerCombo.getValue(),
                stallCombo.getValue()
        );

        horses.add(horse);

        handleClear();

        showAlert(Alert.AlertType.INFORMATION,
                "Success", "Horse added successfully.");
    }

    // Mouse Event: Select a horse
    @FXML
    private void handleRowClick() {

        Horse selected =
                horseTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            return;
        }

        nameField.setText(selected.getName());
        breedCombo.setValue(selected.getBreed());
        ageField.setText(String.valueOf(selected.getAge()));

        if ("Male".equals(selected.getGender())) {
            maleRadio.setSelected(true);
        } else {
            femaleRadio.setSelected(true);
        }

        ownerCombo.setValue(selected.getOwnerID());
        stallCombo.setValue(selected.getStallID());
    }

    // UPDATE: Modify a horse
    @FXML
    private void handleUpdate() {

        Horse selected =
                horseTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Warning", "Please select a horse first.");

            return;
        }

        if (!validateInput()) {
            return;
        }

        Horse updatedHorse = new Horse(
                selected.getHorseID(),
                nameField.getText().trim(),
                breedCombo.getValue(),
                Integer.parseInt(ageField.getText().trim()),
                getGender(),
                ownerCombo.getValue(),
                stallCombo.getValue()
        );

        int index = horses.indexOf(selected);

        horses.set(index, updatedHorse);

        handleClear();

        showAlert(Alert.AlertType.INFORMATION,
                "Success", "Horse updated successfully.");
    }

    // DELETE: Remove a horse
    @FXML
    private void handleDelete() {

        Horse selected =
                horseTable.getSelectionModel().getSelectedItem();

        if (selected == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Warning", "Please select a horse first.");

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

            horses.remove(selected);

            handleClear();

            showAlert(Alert.AlertType.INFORMATION,
                    "Success", "Horse deleted successfully.");
        }
    }

    // Clear the form
    @FXML
    private void handleClear() {

        nameField.clear();
        breedCombo.setValue(null);
        ageField.clear();
        maleRadio.setSelected(true);
        ownerCombo.setValue(null);
        stallCombo.setValue(null);

        horseTable.getSelectionModel().clearSelection();

        updateStatus();
    }

    // Get selected gender
    private String getGender() {

        return maleRadio.isSelected() ? "Male" : "Female";
    }

    // Input validation
    private boolean validateInput() {

        if (nameField.getText().trim().isEmpty()) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter the horse name.");

            return false;
        }

        if (breedCombo.getValue() == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select a breed.");

            return false;
        }

        try {

            int age = Integer.parseInt(
                    ageField.getText().trim());

            if (age < 0) {

                showAlert(Alert.AlertType.WARNING,
                        "Validation Error",
                        "Age cannot be negative.");

                return false;
            }

        } catch (NumberFormatException e) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please enter a valid age.");

            return false;
        }

        if (genderGroup.getSelectedToggle() == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select a gender.");

            return false;
        }

        if (ownerCombo.getValue() == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select an owner.");

            return false;
        }

        if (stallCombo.getValue() == null) {

            showAlert(Alert.AlertType.WARNING,
                    "Validation Error",
                    "Please select a stall.");

            return false;
        }

        return true;
    }

    // Update the status label
    private void updateStatus() {

        statusLabel.setText(
                "Total horses: " + horses.size());
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
        // Already on the Horses page
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