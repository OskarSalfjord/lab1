import java.awt.*;

public abstract class Truck extends Vehicle {
    private boolean CanMove;
    private final int nrDoors;
    public Truck(int nrDoors, Color color, double enginePower, String modelName, double weight, double x, double y, double direction) {
        super(color, enginePower, modelName, weight, x, y, direction);
        this.nrDoors = nrDoors;
        this.CanMove = false;
    }
    protected int getNrDoors() {
        return nrDoors;
    }
    protected void setCanMove(boolean status) {
        this.CanMove = status;
    }
    protected boolean getCanMove() {
        return CanMove;
    }
    @Override
    protected void startEngine(){
        if (!CanMove) {
            throw new IllegalCallerException("This truck is available for loading goods, can not move");
        }
        else {
            super.startEngine();
        }
    }
}
