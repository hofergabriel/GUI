package hofer_gabriel;
import java.util.ArrayList;

public class ApartmentComplex {
    private int currMonth, funds;
    private ArrayList<ArrayList<Apartment>> apartments = new ArrayList<ArrayList<Apartment>>();

    public void increaseRent(int amt){ }
    public void newMonth(){}
    public ApartmentComplex(){ }

    public void clearApartmentComplex(){ apartments.clear(); }
    public void addBuilding(){ apartments.add(new ArrayList<Apartment>()); }
    public void addApartment(int b, Apartment a){ apartments.get(b).add(a); }

}
