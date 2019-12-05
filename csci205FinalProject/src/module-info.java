module csci205FinalProject {

    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;

    opens Networking.client;
    opens Networking.server;
}