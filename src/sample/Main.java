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

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label
        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel,0,0);

        //Name Input
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput,1,0);

        //Password Label
        Label pswLabel = new Label("password");
        GridPane.setConstraints(pswLabel,0,1);

        //Password Input
        TextField pswInput = new TextField();
        pswInput.setPromptText("password");
        GridPane.setConstraints(pswInput,1,1);

        Button logInButton = new Button("Log in");;
        GridPane.setConstraints(logInButton,1,2);

        grid.getChildren().addAll(nameLabel,nameInput,pswInput,pswLabel,logInButton);
        Scene scene = new Scene(grid,400,400);
        window.setScene(scene);



        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        window.setResizable(false);

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