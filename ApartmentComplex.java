package hofer_gabriel;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class ApartmentComplex {
    private int FUNDS=100000;
    private int MAINTENANCE=0;
    private int COLLECTRENT=0;
    private int FILLED=0;
    private ArrayList<ArrayList<Apartment>> apartments = new ArrayList<ArrayList<Apartment>>();

    public void increaseRent(int x){
        for(int i=0;i<apartments.size();i++)
            for(int j=0;j<apartments.get(i).size();j++)
                apartments.get(i).get(j).increaseRent(x);
    }

    public void newMonth(ArrayList<VBox> buildings){
        for(int i=0;i<apartments.size();i++)
            for(int j=0;j<apartments.get(i).size();j++)
                apartments.get(i).get(j).newMonth();
    }

    public void updateView(ArrayList<VBox> buildings){
        for(int i=0;i<apartments.size();i++){
            for(int j=0;j<apartments.get(i).size();j++){
                int floors = apartments.get(i).size()-1;
                ObservableList<Button> children = (ObservableList) buildings.get(i).getChildren();
                int rent = apartments.get(i).get(j).getRent();
                int time = apartments.get(i).get(j).getDuration();
                if(apartments.get(i).get(j) instanceof Empty)
                    children.get(floors-j).setText("Empty");
                if(apartments.get(i).get(j) instanceof Basic)
                    children.get(floors-j).setText("Basic\nRent: "+rent+"\nDuration: "+time);
                if(apartments.get(i).get(j) instanceof Penthouse)
                    children.get(floors-j).setText("Penthouse\nRent: "+rent+"\nDuration: "+time);
            }
        }
    }

    public void updateCollectRent() {
        COLLECTRENT = 0;
        for (int i = 0; i < apartments.size(); i++)
            for (int j = 0; j < apartments.get(i).size(); j++)
                COLLECTRENT+=apartments.get(i).get(j).getRent();
    }

    public void updateMaintenance(){
        MAINTENANCE=0;
        for(int i=0;i<apartments.size();i++)
            for (int j = 0; j < apartments.get(i).size(); j++)
                MAINTENANCE+=apartments.get(i).get(j).getMaintenance();
    }

    public int getCOLLECTRENT(){ return COLLECTRENT; }
    public int getMAINTENANCE(){ return MAINTENANCE; }

    public void updateFunds(ArrayList<VBox> buildings){
        this.updateCollectRent();
        this.updateMaintenance();
        for(int i=0;i<apartments.size();i++)
            for(int j=0;j<apartments.get(i).size();j++)
                FUNDS=FUNDS+this.getCOLLECTRENT()-this.getMAINTENANCE();
    }

    public void updateFILLED(){ FILLED+=1; }
    public int getFILLED(){ return FILLED; }

    public void newEmptyUpdateFunds(){ FUNDS-=20000; }
    public ApartmentComplex(){ }
    public void clearApartmentComplex(){ apartments.clear(); }
    public void addBuilding(){ apartments.add(new ArrayList<Apartment>()); }
    public void addApartment(int b, Apartment a){ apartments.get(b).add(a); }
    public void setApartment(int bui, int flo, Apartment a){ apartments.get(bui).set(flo,a); }
    public int getBuildingHeight(int bui){ return apartments.get(bui).size(); }
    public int getFUNDS(){ return FUNDS; }
}



