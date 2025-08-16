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
    TreeView<String> tree1;
    TreeView<String> tree2;
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


        //                           TABLE VIEW

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
        addButton.setOnAction(e -> tableAddButtonClicked());

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            try {
                tableDeleteButtonClicked();
        } catch (Exception exception) {
                AlertBox ab = new AlertBox();
                ab.setCloseWindowText("Ok");
                ab.display("Warning",exception.getMessage());


        } }
    );

        HBox tableHBox = new HBox();
        tableHBox.setPadding(new Insets(10,10,10,10));
        tableHBox.setSpacing(10);
        tableHBox.getChildren().addAll(nameInput,priceInput,quantityInput,addButton,deleteButton);



        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(nameColumn,priceColumn,quantityColumn);
        //                           TABLE VIEW


        //                          COMBO BOX

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Good Will Hunting",
                "St. Vincent",
                "Blackhat"
        );
        comboBox.setOnAction(e -> System.out.println("User selected: " + comboBox.getValue()));
        comboBox.setEditable(true);
        comboBox.setPromptText("Pick Movie");
        //                          COMBO BOX



        //                           LIST VIEW
        listView = new ListView<>();
        listView.getItems().addAll("Iron Man","Titanic","Contact","Surrogates");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);




        Button viewListButton = new Button("View Selected");
        viewListButton.setOnAction(e -> {
            viewListButtonClicked();
        });

        //                           LIST VIEW




        //              TREE ROOT AND BRANCHES


        //Create Tree1 (DOUBLE-CLICK)
        tree1 = new TreeView<>(getTree());
        tree1.setShowRoot(false);
        tree1.setOnMouseClicked(e -> {
            if(e.getClickCount() == 2 ){
            TreeItem<String> selectedItem = tree1.getSelectionModel().getSelectedItem();
                if(selectedItem != null) {
                    System.out.println(selectedItem.getValue());
                }
            }
        });

        //Create Tree2
        tree2 = new TreeView<>(getTree());
        tree2.setShowRoot(false);
        tree2.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newValue) -> {
            if(newValue != null) {
                System.out.println(newValue.getValue());
            }
        });

        //              TREE ROOT AND BRANCHES



        //                   LAYOUT DESIGN
        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll(tree2);

        Scene scene = new Scene(vBox,500,500);
        window.setScene(scene);
        window.show();
        //                   LAYOUT DESIGN


    }
    //MAIN METHOD END

    public TreeItem<String> getTree() {
        TreeItem<String> root,bucky,megan;
        root = new TreeItem<>();
        root.setExpanded(true);

        bucky = makeBranch("Bucky",root);
        makeBranch("thenewboston",bucky);
        makeBranch("Youtube",bucky);
        makeBranch("Chicken",bucky);

        megan = makeBranch("Megan",root);
        makeBranch("Glitter",megan);
        makeBranch("Makeup",megan);

        return root;

    }


    //Table Delete Button
    public void tableDeleteButtonClicked() throws Exception {
        ObservableList<Product> productSelected , allProducts;
        allProducts = table.getItems();
        if(allProducts.size() <= 1) {
            throw new Exception("You cannot remove the only item on table");
        }
        productSelected = table.getSelectionModel().getSelectedItems();

        productSelected.forEach(allProducts::remove);
    }

    //Table Add Button
    public void tableAddButtonClicked() {
        Product product = new Product();
        String name = product.getName();
        double price = product.getPrice();
        int quantity = product.getQuantity();

        name = this.nameInput.getText();
        if(name.trim().equals("")) {
            AlertBox ab = new AlertBox();
            ab.setCloseWindowText("Ok");
            ab.display("Warning" , "Cannot add unnamed item(s).");
            return;
        }
        product.setName(name);


        String priceTag = this.priceInput.getText().trim();
        try {
            if (priceTag.equals("")) {
                product.setPrice(0.0);
            }else {
            price = Double.parseDouble(priceTag);}
        } catch (NumberFormatException e) {
            AlertBox ab = new AlertBox();
            ab.setCloseWindowText("Ok");
            ab.display("Warning: Invalid input","'" + priceTag + "' is not a valid price value.");
            return;
        }
        product.setPrice(price);

        String quantityTag = this.quantityInput.getText().trim();
        try {
            if (quantityTag.equals("")) {
                product.setQuantity(0);
            }else {
                quantity = Integer.parseInt(quantityTag);}
        } catch (NumberFormatException e) {
            AlertBox ab = new AlertBox();
            ab.setCloseWindowText("Ok");
            ab.display("Warning: Invalid input","'" + quantityTag + "' is not a valid quantity value.");
            return;
        }
        product.setQuantity(quantity);

        table.getItems().addAll(product);


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

    private void viewListButtonClicked(){
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