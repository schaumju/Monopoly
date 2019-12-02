module csci205FinalProject {

    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;

    opens Main;
    opens Networking.client;
    opens Networking.server;
}