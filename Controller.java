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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import static java.awt.Color.*;


/* Put event handlers here */
public class Controller {
    private ApartmentComplex apartmentComplex = new ApartmentComplex();
    private int MONTH=0, FUNDS=20000, RENTTOCOLLECT=0, FILLED=0;

    public Controller(){ }

    public void setActionBuildingCount(
            Button b, int cnt, BorderPane BPane, GridPane center,
            ArrayList<VBox> buildings, ArrayList<Button> buildingButtons, ToggleGroup group){
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList<Button> btn = new ArrayList<Button>();
                HBox topHBox;
                for(int i=0;i<cnt;i++){
                    Button b = new Button();
                    b.setMinSize(50,30);
                    b.setMaxSize(500,30);
                    b.setPrefSize(500,30);
                    b.setText("Build");
                    btn.add(b);
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
                    btn.get(i).setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            /*---------- ADD FLOOR/APARTMENT ----------*/
                            System.out.println("1radio: "+group.getSelectedToggle());
                            String txt = ((RadioButton) group.getSelectedToggle()).getText();
                            if(txt=="Empty") apartmentComplex.addApartment(finalI, new Empty());
                            if(txt=="Basic") apartmentComplex.addApartment(finalI, new Basic());
                            if(txt=="Penthouse") apartmentComplex.addApartment(finalI, new Penthouse());

                            buildings.get(finalI).setAlignment(Pos.BOTTOM_RIGHT);
                            Button b = new Button();
                            if(txt=="Empty") b.setText("Empty");
                            if(txt=="Basic") b.setText("Basic");
                            if(txt=="Penthouse") b.setText("Penthouse");
                            b.setMinSize(10,50);
                            b.setMaxSize(400,200);
                            b.setPrefSize(400,50);
                            buildings.get(finalI).getChildren().add(b);
                        }
                    });

                }
                BPane.setCenter(center);
            }
        });
    }

    public void setActionNewMonth(Button newMonth, Text month){
        newMonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MONTH+=1;
                String txt = "Month: "+String.valueOf(MONTH);
                month.setText(txt);
            }
        });
    }

    public void setActionBuild(Button btn, VBox vbox){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox.setAlignment(Pos.BOTTOM_RIGHT);
                Button b = new Button("Empty");
                b.setMinSize(10,50);
                b.setMaxSize(200,200);
                b.setPrefSize(200,50);
                vbox.getChildren().add(b);
                vbox.setSpacing(0);
            }
        });
    }

    /*
    public void setActionIncreaseRent(){
        newMonth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MONTH+=1;
                String txt = "Month: "+String.valueOf(MONTH);
                month.setText(txt);
            }
        });
    }
    */


}


//for(int i=0;i<cnt;i++) GridPane.setFillWidth(buildings.get(i), true);
//for(int i=0;i<cnt;i++) GridPane.setFillHeight(buildings.get(i), true);
