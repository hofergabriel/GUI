package hofer_gabriel;
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
    public void showRents(){
        System.out.println("rows: "+apartments.size());
        for(int i=0;i<apartments.size();i++){
            System.out.println("cols: "+apartments.get(i).size());
            for(int j=0;j<apartments.get(i).size();j++){
                System.out.println("rent!: "+apartments.get(i).get(j).getRent());
                if(apartments.get(i).get(j) instanceof Empty)
                    System.out.println("EMPTY");
                if(apartments.get(i).get(j) instanceof Basic)
                    System.out.println("BASIC");
                if(apartments.get(i).get(j) instanceof Penthouse)
                    System.out.println("PENTHOUSE");
            }
        }

    }


    public ApartmentComplex(){ }
    public void clearApartmentComplex(){ apartments.clear(); }
    public void addBuilding(){ apartments.add(new ArrayList<Apartment>()); }
    public void addApartment(int b, Apartment a){ apartments.get(b).add(a); }
}
