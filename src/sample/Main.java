package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Arrays;
import java.util.stream.Collectors;


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



        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Pineapples");
        choiceBox.getItems().add("Bananas");

        choiceBox.setValue("Apples");

        Button button = new Button("Click Me");
        button.setOnAction(e -> {
            getChoice(choiceBox);
        });


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(choiceBox,button);

        Scene scene = new Scene(layout,350,350);
        window.setScene(scene);

        window.show();


    }
    private void getChoice(ChoiceBox<String> choiceBox){
        String text = choiceBox.getValue();
        System.out.println(text);
    }

    private void handleOptions(CheckBox... boxes) {
        System.out.println(String.join("\n", Arrays.stream(boxes).filter(CheckBox::isSelected).map(CheckBox::getText).collect(Collectors.toList())));
    }


    private boolean isInt(TextField input) {
        String text = input.getText();
        try{
        int number = Integer.parseInt(text);
        System.out.println("Number: " + number +" ;Valid!");
        return true;
        } catch (NumberFormatException e) {
            System.err.println("Error: '" + text + "' is not a number");
        }
        return false;
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