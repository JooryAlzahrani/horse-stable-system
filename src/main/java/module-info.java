module com.mycompany.welcomecard.horse.stable.system {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.welcomecard.horse.stable.system to javafx.fxml;
    exports com.mycompany.welcomecard.horse.stable.system;
}
