package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SecondMain  extends Application {
    Stage window;
    BorderPane layout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        window.setTitle("Title");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        Menu filemenu = new Menu("File");

        filemenu.getItems().add(new MenuItem("New Project..."));
        filemenu.getItems().add(new MenuItem("New Module..."));
        filemenu.getItems().add(new MenuItem("Import Project..."));

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu);



        layout = new BorderPane();
        layout.setTop(menuBar);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll();

        Scene scene = new Scene(layout,500,500);
        window.setScene(scene);

        window.show();

    }

    private void closeProgram() {
        ConfirmBox cb = new ConfirmBox();
        boolean answer = cb.display("Close Program","Are you sure?\nClose Program");
        if(answer) {
            System.out.println("Closing...");
            window.close();
        }

    }
}
