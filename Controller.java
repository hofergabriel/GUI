package hofer_gabriel;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import java.util.ArrayList;


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
                    Apartment A;
                    apartmentComplex.addApartment(finalI, A=new Empty());

                    buildings.get(finalI).setAlignment(Pos.BOTTOM_RIGHT);
                    Button b1 = new Button();
                    b1.setText("Empty");
                    Apartment finalA1 = A;
                    int currentheight = apartmentComplex.getBuildingHeight(finalI)-1;

                    b1.setOnAction(event2 -> {
                        int heightbefore = apartmentComplex.getBuildingHeight(finalI)-1;
                        String txt = ((RadioButton) group.getSelectedToggle()).getText();
                        System.out.println("heightbefore: "+heightbefore);
                        System.out.println("currentheight: "+currentheight+"\n");

                        if(txt=="Empty") {
                            b1.setText("Empty");
                            Apartment A2 = new Empty();
                            apartmentComplex.setApartment(finalI,currentheight,A2);
                        }
                        if(txt=="Basic") {
                            b1.setText("Basic\nDuration: " + finalA1.getDuration() + "\nRent: " + finalA1.getRent());
                            Apartment A2 = new Basic();
                            apartmentComplex.setApartment(finalI,currentheight,A2);
                        }
                        if(txt=="Penthouse") {
                            b1.setText("Penthouse\nDuration: " + finalA1.getDuration() + "\nRent: " + finalA1.getRent());
                            Apartment A2 = new Penthouse();
                            apartmentComplex.setApartment(finalI,currentheight,A2);
                        }
                        apartmentComplex.updateView(buildings);

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
            apartmentComplex.increaseRent(Integer.parseInt(increaseRentTextField.getText()));
            apartmentComplex.updateView(buildings);
        });
    }

    /* UPDATE THE MONTH */
    public void setActionNewMonth(Button newMonth, Text month, ArrayList<VBox> buildings){
        newMonth.setOnAction(event -> {
            MONTH+=1;
            String txt = "Month: "+String.valueOf(MONTH);
            month.setText(txt);
            apartmentComplex.newMonth(buildings);
            apartmentComplex.updateView(buildings);
        });
    }



}


