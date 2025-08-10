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
    ComboBox comboBox;
    ListView listView;
    TreeView<String> tree;

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


        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Good Will Hunting",
                "St. Vincent",
                "Blackhat"
        );
        comboBox.setOnAction(e -> System.out.println("User selected: " + comboBox.getValue()));
        comboBox.setEditable(true);
        comboBox.setPromptText("Pick Movie");

        listView = new ListView<>();
        listView.getItems().addAll("Iron Man","Titanic","Contact","Surrogates");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);




        Button button = new Button("Click Me");
        button.setOnAction(e -> {
            buttonClicked();
        });

        TreeItem<String> root,bucky,megan;

        //Root
        root = new TreeItem<>();
        root.setExpanded(true);

        //Bucky
        bucky = makeBranch("Bucky",root);
        makeBranch("thenewboston",bucky);
        makeBranch("Youtube",bucky);
        makeBranch("Chicken",bucky);

        //Megan
        megan = makeBranch("Megan",root);
        makeBranch("Glitter",megan);
        makeBranch("Makeup",megan);

        //Create Tree
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newValue) -> {
            if(newValue != null) {
                System.out.println(newValue.getValue());
            }
        });


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().add(tree);
        Scene scene = new Scene(layout,350,350);
        window.setScene(scene);

        window.show();


    }

    public TreeItem<String> makeBranch(String title,TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    private void buttonClicked(){
        //for List View
        System.out.println(listView.getSelectionModel().getSelectedItems().stream().collect(Collectors.joining(",")));
    }

    private void printText() {
        //For Combo Box
        System.out.println(comboBox.getValue());
    }

    private void getChoice(ChoiceBox<String> choiceBox){
        //For Choice Box
        String text = choiceBox.getValue();
        System.out.println(text);
    }

    private void handleOptions(CheckBox... boxes) {
        //For Choice Box
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