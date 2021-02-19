package hofer_gabriel;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.*;
//import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import static java.awt.Color.*;


/* Put event handlers here */
public class Controller {
    private ApartmentComplex apartmentComplex = new ApartmentComplex();
    private int MONTH=0, FUNDS=20000, RENTTOCOLLECT=0, FILLED=0;

    public void setActionBuildingCount(
            Button b, int cnt, BorderPane BPane, GridPane center,
            ArrayList<VBox> buildings, ArrayList<Button> buildingButtons,
            ToggleGroup group){

        b.setOnAction(event -> {
            ArrayList<Button> btn = new ArrayList<Button>();
            HBox topHBox;
            for(int i=0;i<cnt;i++){
                Button b2 = new Button();
                b2.setMinSize(50,30);
                b2.setMaxSize(500,30);
                b2.setPrefSize(500,30);
                b2.setText("Build");
                btn.add(b2);
            }
            if(cnt==3) topHBox = new HBox(btn.get(0), btn.get(1), btn.get(2));
            else if(cnt==4) topHBox = new HBox(btn.get(0), btn.get(1), btn.get(2), btn.get(3));
            else topHBox = new HBox(btn.get(0), btn.get(1), btn.get(2), btn.get(3), btn.get(4));
            topHBox.setMargin(btn.get(0), new Insets(0,0,0,100));
            topHBox.setMargin(btn.get(btn.size()-1), new Insets(0,150,0,0));
            ObservableList topHBoxChildren = topHBox.getChildren();
            topHBoxChildren.addAll();
            BPane.setTop(topHBox);

            /*---------- SET CENTER ----------*/
            center.getChildren().clear();
            center.getColumnConstraints().clear();
            buildings.clear();
            buildingButtons.clear();
            center.setGridLinesVisible(true);

            for(int i=0;i<cnt;i++) buildings.add(new VBox());
            for(int i=0;i<cnt;i++) center.add(buildings.get(i),i,0,1,1);
            for(int i=0;i<cnt;i++) GridPane.setVgrow(buildings.get(i), Priority.ALWAYS);

            ArrayList<ColumnConstraints> columnConstraints = new ArrayList<ColumnConstraints>();
            for(int i=0;i<cnt;i++) columnConstraints.add(new ColumnConstraints());
            for(int i=0;i<cnt;i++) columnConstraints.get(i).setPercentWidth(100/cnt);
            for(int i=0;i<cnt;i++) center.getColumnConstraints().add(columnConstraints.get(i));

            /*---------- FLOORS ----------*/
            apartmentComplex.clearApartmentComplex();
            for(int i=0;i<cnt;i++) apartmentComplex.addBuilding();

            /*---------- CONTROLLER ----------*/
            for(int i=0;i<cnt;i++) {
                int finalI = i;
                btn.get(i).setOnAction(event1 -> {
                    /*---------- ADD FLOOR/APARTMENT ----------*/
                    System.out.println("1radio: "+group.getSelectedToggle());
                    String txt = ((RadioButton) group.getSelectedToggle()).getText();

                    Empty E=null; Basic B=null; Penthouse P=null;
                    if(txt=="Empty") apartmentComplex.addApartment(finalI, E=new Empty());
                    if(txt=="Basic") apartmentComplex.addApartment(finalI, B=new Basic());
                    if(txt=="Penthouse") apartmentComplex.addApartment(finalI, P=new Penthouse());

                    buildings.get(finalI).setAlignment(Pos.BOTTOM_RIGHT);
                    Button b1 = new Button();
                    b1.setText("Empty");
                    Basic finalB = B;
                    Penthouse finalP = P;
                    b1.setOnAction(event2 -> {
                        String txt2 = ((RadioButton) group.getSelectedToggle()).getText();

                        if(txt2=="Empty") b1.setText("Empty");
                        if(txt2=="Basic") b1.setText("Basic\nDuration: 0\nRent: 0");
                        if(txt2=="Penthouse") b1.setText("Penthouse"); //\nDuration: "+ finalP.getRent()+"\nRent: "+ finalP.getRent());
                        System.out.println("remodeling");
                        System.out.println("txt: "+txt2);

                    });

                    b1.setMinSize(10,60);
                    b1.setMaxSize(400,200);
                    b1.setPrefSize(400,60);
                    buildings.get(finalI).getChildren().add(0, b1);
                });

            }
            BPane.setCenter(center);
        });
    }

    /* UPDATE THE MONTH */
    public void setActionNewMonth(Button newMonth, Text month){
        newMonth.setOnAction(event -> {
            MONTH+=1;
            String txt = "Month: "+String.valueOf(MONTH);
            month.setText(txt);
        });
    }

    /* BUILD BUTTONS */
    public void setActionBuild(Button btn, VBox vbox){
        btn.setOnAction(event -> {
            vbox.setAlignment(Pos.BOTTOM_RIGHT);
            Button b = new Button("Empty");
            b.setMinSize(10,50);
            b.setMaxSize(200,200);
            b.setPrefSize(200,50);
            vbox.getChildren().add(b);
            vbox.setSpacing(0);
        });
    }

    /* INCREASE THE RENT */
    public void setActionIncreaseRent(Button increaseRentButton, TextField increaseRentTextField, ArrayList<VBox> buildings){
        /* MAP INDIVIDUAL views to their corresponding model */
        increaseRentButton.setOnAction(event -> {
            System.out.println("increase rent by: "+increaseRentTextField.getText());
            apartmentComplex.increaseRent(Integer.parseInt(increaseRentTextField.getText()));
            apartmentComplex.updateView(buildings);
        });
    }

}


//for(int i=0;i<cnt;i++) GridPane.setFillWidth(buildings.get(i), true);
//for(int i=0;i<cnt;i++) GridPane.setFillHeight(buildings.get(i), true);
