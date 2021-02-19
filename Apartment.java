package hofer_gabriel;

public class Apartment {
    private int rent=0, maintenance=200, timeSinceRemodel=0;
    public void increaseRent(int x){ rent+=x; }
    public void newMonth(){ timeSinceRemodel+=1; }
    public int getRent(){ return rent; }
    public int getDuration(){ return timeSinceRemodel; }
    public int getMaintenance(){ return maintenance; }
    public void setRent(int x){ rent=x; }
    public void setMaintenance(int x){ maintenance=x; }
}
