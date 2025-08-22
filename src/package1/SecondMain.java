package package1;

import exampleClasses.ConfirmBox;
import exampleClasses.Person;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SecondMain  extends Application{
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





        //                                  PACKED METHODS
        Button runBindingExample = new Button("Binding Example");
        runBindingExample.setOnAction(e -> {
            try {
                simpleBindingExample();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        VBox propertiesVBox = getPropertiesExample();
        VBox bindingExampleVBox = textBindingExample();
        MenuBar menuBar = getMenuBar();
        //                                  PACKED METHODS


        //                   LAYOUT DESIGN
        layout = new BorderPane();
        layout.setTop(menuBar);

        VBox vBox = new VBox(10);
        vBox.setPadding(new Insets(20,20,20,20));
        vBox.getChildren().addAll();

        Scene scene = new Scene(bindingExampleVBox,500,500);
        window.setScene(scene);

        window.show();
        //                   LAYOUT DESIGN


    }
    //                                         MAIN METHOD END

    private VBox textBindingExample() {
        TextField userInput = new TextField();
        userInput.setMaxWidth(200);
        Label firstLabel = new Label("Welcome to the Site ");
        Label secondLabel = new Label();

        HBox bindingTextHBox = new HBox(firstLabel,secondLabel);
        bindingTextHBox.setAlignment(Pos.CENTER);

        VBox bindingExampleVBox = new VBox(10,userInput,bindingTextHBox);
        bindingExampleVBox.setAlignment(Pos.CENTER);

        secondLabel.textProperty().bind(userInput.textProperty());

        return bindingExampleVBox;
    }

    private void simpleBindingExample() throws InterruptedException {
        IntegerProperty x = new SimpleIntegerProperty(3);
        IntegerProperty y = new SimpleIntegerProperty();
        System.out.println("X: "+ x.getValue() + " Y: " + y.getValue());
        y.bind(x.multiply(5));
        Thread.sleep(2000);
        x.setValue(x.getValue() + 2);
        System.out.println("X: "+ x.getValue() + " Y: " + y.getValue());
        y.unbind();
        System.out.println("Y unbound");
        Thread.sleep(2000);
        x.setValue(x.getValue() + 2);
        System.out.println("X: "+ x.getValue() + " Y: " + y.getValue());

    }
    private VBox getPropertiesExample(){
        Person eren = new Person();
        Label name = new Label("Their name");

        TextField nameInput = new TextField();

        nameInput.setPromptText("Enter New Name");

        Button changeNameButton = new Button("Change Name");

        changeNameButton.setOnAction(e -> {
            eren.setFirstName(nameInput.getText());
        });

        eren.firstNameProperty().addListener( (v,oldValue,newValue) -> {
            System.out.println("Name Changed to: " + "'" + newValue + "'");
            System.out.println("firstNameProperty(): " + eren.firstNameProperty());
            System.out.println("getFirstName(): " + eren.getFirstName());
            name.setText(newValue);
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(nameInput,changeNameButton,name);
        return vBox;
    }

    //                                          MENU DESIGNING
    private MenuBar getMenuBar(){
        Menu filemenu = new Menu("File");
        getFileMenuItems(filemenu);

        Menu editMenu = new Menu("_Edit");
        editMenu.getItems().add(new MenuItem("Cut"));
        editMenu.getItems().add(new MenuItem("Copy"));

        MenuItem paste = new MenuItem("Paste");
        paste.setOnAction(e -> System.out.println("paste..."));
        paste.setDisable(true);
        editMenu.getItems().add(paste);

        Menu helpMenu = new Menu("Help");
        CheckMenuItem showLines = new CheckMenuItem("Show Line Numbers");
        showLines.setOnAction(e -> {
            if (showLines.isSelected()) {
                System.out.println("Display Lines");
            } else {
                System.out.println("Hiding Lines Now");
            }
        });
        CheckMenuItem autoSave = new CheckMenuItem("Enable AutoSave");
        autoSave.setSelected(true);
        helpMenu.getItems().addAll(showLines,autoSave);

        Menu difficultyMenu = getDifficultyMenu();
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(filemenu,editMenu,helpMenu,difficultyMenu);

        return menuBar;
    }

    private Menu getDifficultyMenu() {
        Menu difficultyMenu = new Menu("Difficulty");
        ToggleGroup difficultyToggle = new ToggleGroup();
        RadioMenuItem ez = new RadioMenuItem("Easy");
        RadioMenuItem mid = new RadioMenuItem("Medium");
        RadioMenuItem hard = new RadioMenuItem("Hard");

        ez.setToggleGroup(difficultyToggle);
        ez.setStyle("-fx-text-fill: limeGreen");
        mid.setToggleGroup(difficultyToggle);
        mid.setStyle("-fx-text-fill: Orange");
        hard.setToggleGroup(difficultyToggle);
        hard.setStyle("-fx-text-fill: Red");

        difficultyMenu.getItems().addAll(ez,mid,hard);

        return difficultyMenu;
    }


    private void getFileMenuItems(Menu menu) {
        MenuItem newFile = new MenuItem("New...");
        newFile.setOnAction(e -> System.out.println("Create a new File..."));
        menu.getItems().add(newFile);
        menu.getItems().add(new MenuItem("Open..."));
        menu.getItems().add(new MenuItem("Save..."));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Settings..."));
        menu.getItems().add(new SeparatorMenuItem());
        menu.getItems().add(new MenuItem("Exit..."));

    }
    //                                          MENU DESIGNING


    private void closeProgram() {
        ConfirmBox cb = new ConfirmBox();
        boolean answer = cb.display("Close Program","Are you sure?\nClose Program");
        if(answer) {
            System.out.println("Closing...");
            window.close();
        }

    }
}
