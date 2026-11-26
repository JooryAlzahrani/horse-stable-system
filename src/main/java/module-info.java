module com.mycompany.welcomecard.horse.stable.system {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.welcomecard.horsestablesystem to javafx.fxml;
    opens com.mycompany.welcomecard.horsestablesystem.controller to javafx.fxml;
    opens com.mycompany.welcomecard.horsestablesystem.model to javafx.base;
    exports com.mycompany.welcomecard.horsestablesystem;
}
