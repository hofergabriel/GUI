package hofer_gabriel;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Layout {
    private Controller controller;
    private ArrayList<VBox> buildings;
    private GridPane center;
    private ArrayList<Button> buildingButtons;
    public Layout(Stage primaryStage){
        controller = new Controller();

        /*---------- BUILD BUTTONS ----------*/
        buildingButtons = new ArrayList<Button>();
        for(int i=0;i<3;i++){
            Button b = new Button();
            b.setMinSize(50,30);
            b.setMaxSize(500,30);
            b.setPrefSize(500,30);
            b.setText("Build");
            buildingButtons.add(b);
        }

        /*---------- RIGHT BUTTONS & TEXT ----------*/
        Button newMonth = new Button();
        Button increaseRentButton = new Button();
        TextField increaseRentTextfield = new TextField("23");

        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("Empty");
        RadioButton button2 = new RadioButton("Basic");
        RadioButton button3 = new RadioButton("Penthouse");
        button1.setToggleGroup(group);
        button2.setToggleGroup(group);
        button3.setToggleGroup(group);

        Button threeBuildings = new Button();
        Button fourBuildings = new Button();
        Button fiveBuildings = new Button();

        newMonth.setText("New Month");
        increaseRentButton.setText("Increase Rent");
        threeBuildings.setText("3 Buildings");
        fourBuildings.setText("4 Buildings");
        fiveBuildings.setText("5 Buildings");

        /*----------------------------------------------------------------------------------*/
        /*---------- LEFT TEXT ----------*/
        Text rentToCollect = new Text("Rent to Collect: ");
        Text maintenance = new Text("Maintenance: ");
        Text filled = new Text("Filled: ");

        /*---------- BOTTOM TEXT ----------*/
        Text month = new Text("Month: ");
        Text funds = new Text("Funds ");

        /*---------- RIGHT VBOX ----------*/
        VBox rightVBox = new VBox();
        ObservableList rightVBoxChildren = rightVBox.getChildren();
        rightVBoxChildren.addAll(newMonth, increaseRentButton, increaseRentTextfield,
                button1, button2, button3, threeBuildings, fourBuildings, fiveBuildings);
        rightVBox.setSpacing(5);

        /*---------- LEFT VBOX ----------*/
        VBox leftVBox = new VBox();
        ObservableList leftVBoxChildren = leftVBox.getChildren();
        leftVBoxChildren.addAll(rentToCollect, maintenance, filled);
        leftVBox.setMargin(rentToCollect, new Insets(0,15,0,0));

        /*---------- BOTTOM VBOX ----------*/
        VBox bottomVBox = new VBox();
        ObservableList bottomVBoxChildren = bottomVBox.getChildren();
        bottomVBoxChildren.addAll(month, funds);

        /*---------- TOP HBOX ----------*/
        HBox topHBox = new HBox(buildingButtons.get(0), buildingButtons.get(1), buildingButtons.get(2));
        topHBox.setMargin(buildingButtons.get(0), new Insets(0,0,0,100));
        topHBox.setMargin(buildingButtons.get(buildingButtons.size()-1), new Insets(0,150,0,0));
        ObservableList topHBoxChildren = topHBox.getChildren();
        topHBoxChildren.addAll();

        /*---------- CENTER HBOX OF VBOXs----------*/
        buildings = new ArrayList<VBox>();

        center = new GridPane();
        for(int i=0;i<3;i++) buildings.add(new VBox());
        center.add(buildings.get(0), 0,0,1,1);
        center.add(buildings.get(1), 1,0,1,1);
        center.add(buildings.get(2), 2,0,1,1);
        GridPane.setVgrow(buildings.get(0), Priority.ALWAYS);
        GridPane.setVgrow(buildings.get(1), Priority.ALWAYS);
        GridPane.setVgrow(buildings.get(2), Priority.ALWAYS);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPercentWidth(33);
        col2.setPercentWidth(33);
        col3.setPercentWidth(33);
        center.getColumnConstraints().addAll(col1,col2,col3);
        center.setPrefWidth(400);

        /*---------- make BORDERPANE ----------*/
        BorderPane BPane = new BorderPane();
        BPane.setTop(topHBox);
        BPane.setLeft(leftVBox);
        BPane.setRight(rightVBox);
        BPane.setCenter(center);
        BPane.setBottom(bottomVBox);
        Scene scene = new Scene(BPane,800,400);
        primaryStage.setScene(scene);
        primaryStage.show();

        /*----------------------------------------------------------------------------------*/
        /*---------- CONTROLLER STUFF ----------*/
        controller.setActionBuildingCount(threeBuildings,3, BPane,center,buildings,buildingButtons);
        controller.setActionBuildingCount(fourBuildings,4, BPane,center,buildings,buildingButtons);
        controller.setActionBuildingCount(fiveBuildings,5, BPane,center,buildings,buildingButtons);
        controller.setActionNewMonth(newMonth, month);

        controller.setActionBuild(buildingButtons.get(0), buildings.get(0));
        controller.setActionBuild(buildingButtons.get(1), buildings.get(1));
        controller.setActionBuild(buildingButtons.get(2), buildings.get(2));

    }
}
