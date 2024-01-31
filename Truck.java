import java.awt.*;

public abstract class Truck extends Car {
    private boolean CanMove;


    public Truck(int nrDoors, Color color, double enginePower, String modelName, double weight) {
        super(nrDoors, color, enginePower, modelName, weight, 0, 0, 0, false);
        this.CanMove = true;
    }
    protected void setCanMove(boolean status) {
        this.CanMove = status;
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
