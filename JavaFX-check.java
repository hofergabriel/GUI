package hofer_gabriel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        TextField textField = new TextField("23");

        Button btn_negate = new Button();
        btn_negate.setText("negate");
        btn_negate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("negate");
                textField.setText(String.valueOf(-1 * Integer.parseInt(textField.getText())));
            }
        });

        Button btn_2x = new Button();
        btn_2x.setText("2x");
        btn_2x.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("2x");
                textField.setText(String.valueOf(2 * Integer.parseInt(textField.getText())));
            }
        });

        VBox vbox = new VBox(5); // 5 is the spacing between elements in the VBox
        vbox.getChildren().addAll(btn_2x, btn_negate, textField);

        StackPane root = new StackPane();
        root.getChildren().add(vbox);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}
