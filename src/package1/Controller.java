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

public class Controller {

    public Button button;

    public void handleButtonClick() {
        System.out.println("Run sum code");
        button.setText("Task already done");
    }

}
