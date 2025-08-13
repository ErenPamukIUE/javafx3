package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    TableView<Product> table;
    TextField nameInput , priceInput , quantityInput;


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

        TableColumn<Product,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Product,Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(200);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        TableColumn<Product,Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

        //Name Input
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(100);

        //Price Input
        priceInput = new TextField();
        priceInput.setPromptText("Price");
        priceInput.setMinWidth(100);

        //Quantity Input
        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity ");
        quantityInput.setMinWidth(100);

        //Table Input Button
        Button addButton = new Button("Add");
        Button deleteButton = new Button("Delete");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deleteButton);



        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn,priceColumn,quantityColumn);


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
        layout.getChildren().addAll(table,hBox);
        Scene scene = new Scene(layout,350,350);
        window.setScene(scene);

        window.show();


    }

    public ObservableList<Product> getProduct(){
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product("Laptop",859.00,20));
        products.add(new Product("Bear Carpet",3899.00,1));
        products.add(new Product("Squirrell Statue",1050.00,3));
        products.add(new Product("Bouncy Ball",55.00,50));
        return products;


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