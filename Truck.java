import java.awt.*;

public abstract class Truck extends Vehicle {
    private boolean Working;
    private final int nrDoors;
    public Truck(int nrDoors, Color color, double enginePower, String modelName, double weight, double x, double y, double direction) {
        super(color, enginePower, modelName, weight, 0, 0, 0);
        this.nrDoors = nrDoors;
        this.Working = false;
    }
    protected int getNrDoors() {
        return nrDoors;
    }
    protected void setWorking(boolean status) {
        this.Working = status;
    }
    @Override
    protected void startEngine(){
        if (Working) {
            throw new IllegalCallerException("This truck is available for loading goods, can not move");
        }
        else {
            super.startEngine();
        }
    }
}
