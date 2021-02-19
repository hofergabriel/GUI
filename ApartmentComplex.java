package hofer_gabriel;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ApartmentComplex {
    private int currMonth, funds;
    private ArrayList<ArrayList<Apartment>> apartments = new ArrayList<ArrayList<Apartment>>();

    public void increaseRent(int x){
        for(int i=0;i<apartments.size();i++){
            for(int j=0;j<apartments.get(i).size();j++){
                apartments.get(i).get(j).increaseRent(x);
            }
        }
    }

    public void newMonth(){
        for(int i=0;i<apartments.size();i++){
            for(int j=0;j<apartments.get(i).size();j++){
                apartments.get(i).get(j).newMonth();
            }
        }
    }

    public void updateView(ArrayList<VBox> buildings){
        System.out.println("rows: "+apartments.size());
        for(int i=0;i<apartments.size();i++){
            System.out.println("cols: "+apartments.get(i).size());
            for(int j=0;j<apartments.get(i).size();j++){
                System.out.println("rent!: "+apartments.get(i).get(j).getRent());

                ObservableList<Button> children = (ObservableList) buildings.get(i).getChildren();
                children.get(j).setText("gabedean!");
                int rent = apartments.get(i).get(j).getRent();
                int time = apartments.get(i).get(j).getDuration();
                if(apartments.get(i).get(j) instanceof Empty) children.get(j).setText("Empty");
                if(apartments.get(i).get(j) instanceof Basic)
                    children.get(j).setText("Basic\nRent: "+rent+"\nDuration: "+time);
                if(apartments.get(i).get(j) instanceof Penthouse)
                    children.get(j).setText("Penthouse\nRent: "+rent+"\nDuration: "+time);

            }
        }

    }

    public ApartmentComplex(){ }
    public void clearApartmentComplex(){ apartments.clear(); }
    public void addBuilding(){ apartments.add(new ArrayList<Apartment>()); }
    public void addApartment(int b, Apartment a){ apartments.get(b).add(a); }
}
