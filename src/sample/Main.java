package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;


public class Main extends Application {

    Stage window;

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

        HBox topMenu = new HBox();
        Button buttonA = new Button("File");
        Button buttonB = new Button("Edit");
        Button buttonC = new Button("View");
        topMenu.getChildren().addAll(buttonA,buttonB,buttonC);

        VBox leftMenu = new VBox();
        Button buttonD = new Button("B1");
        Button buttonE = new Button("B2");
        Button buttonF = new Button("B3");
        leftMenu.getChildren().addAll(buttonD,buttonE,buttonF);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);


        StackPane layout = new StackPane();
        Scene scene = new Scene(borderPane,800,600);
        window.setScene(scene);
        window.show();


    }

    private void closeProgram() {
        ConfirmBox cb = new ConfirmBox();
        boolean answer = cb.display("Close Program","Are you sure?\nClose Program");
        if(answer) {
            window.close();
        }

    }
}