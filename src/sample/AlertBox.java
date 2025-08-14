package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.*;

public class AlertBox {
    String closeWindowText;

    public AlertBox() {
    this.closeWindowText = "Close this Window";
    }

    public void display(String title, String message){
        Stage window = new Stage();

        Label label = new Label();
        label.setText(message);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        if (message.length()<50) {
        window.setMinWidth(320);
        window.setMinHeight(320);
        } else {
            window.setMinWidth(800);
            window.setMinHeight(600);

        }


        Button closeButton = new Button(closeWindowText);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(3);
        layout.getChildren().addAll(label,closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    public String getCloseWindowText() {
        return closeWindowText;
    }

    public void setCloseWindowText(String closeWindowText) {
        this.closeWindowText = closeWindowText;
    }
}
