package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
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

        TextField nameInput = new TextField();
        Button button = new Button("Click me");
        button.setOnAction(e -> {
            isInt(nameInput);
        });


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(nameInput,button);

        Scene scene = new Scene(layout,350,350);
        window.setScene(scene);

        window.show();


    }
    private boolean isInt(TextField input) {
        String text = input.getText();
        try{
        int age = Integer.parseInt(text);
            System.out.println("User is: " + age + " years old");
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