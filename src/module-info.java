module javafx {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens package1;
    opens exampleClasses;
}