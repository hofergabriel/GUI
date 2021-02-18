package hofer_gabriel;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import static java.awt.Color.*;


/* Put event handlers here */
public class Controller {
    private ApartmentComplex apartmentComplex;
    private int MONTH;
    private int FUNDS;
    private int RENTTOCOLLECT;
    private int FILLED;
    public Controller(){
        apartmentComplex = new ApartmentComplex();
        MONTH=0;
        FUNDS=20000;
        RENTTOCOLLECT=0;
        FILLED=0;
    }

    public void setActionBuildingCount(Button b, int cnt, BorderPane BPane){
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
                if(cnt==3) { topHBox = new HBox(btn.get(0), btn.get(1), btn.get(2)); }
                else if(cnt==4) { topHBox = new HBox(btn.get(0), btn.get(1), btn.get(2), btn.get(3)); }
                else { topHBox = new HBox(btn.get(0), btn.get(1), btn.get(2), btn.get(3), btn.get(4)); }
                topHBox.setMargin(btn.get(0), new Insets(0,0,0,100));
                topHBox.setMargin(btn.get(btn.size()-1), new Insets(0,150,0,0));
                ObservableList topHBoxChildren = topHBox.getChildren();
                topHBoxChildren.addAll();
                BPane.setTop(topHBox);
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

}
