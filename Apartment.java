package hofer_gabriel;

public class Apartment {
    private int rent, maintenance, remodel, timeSinceRemodel;
    public void increaseRent(int x){ rent+=x; }
    public void newMonth(){ timeSinceRemodel+=1; }
    public int getRent(){ return rent; }
}
