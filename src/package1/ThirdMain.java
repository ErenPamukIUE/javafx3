package package1;

import exampleClasses.AlertBox;
import exampleClasses.ConfirmBox;
import exampleClasses.Product;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.stream.Collectors;

import static javafx.application.Application.launch;

public class ThirdMain extends Application{
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        Parent root = FXMLLoader.load(getClass().getResource("otherFile.fxml"));
        window.setTitle("Program Title");
        window.setScene(new Scene(root,800,600));
        window.show();


    }

    private void closeProgram() {
        ConfirmBox cb = new ConfirmBox();
        boolean answer = cb.display("Close Program", "Are you sure?\nClose Program");
        if (answer) {
            System.out.println("Closing...");
            window.close();
        }

    }
}
