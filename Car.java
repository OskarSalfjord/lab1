import java.awt.*;
public abstract class Car extends Vehicle{
    private final int nrDoors;

    protected Car(int nrDoors,Color color, double enginePower, String modelName, double weight, double x, double y, double direction) {
        super(color, enginePower, modelName, weight, x, y, direction);
        this.nrDoors = nrDoors;
    }
    protected int getNrDoors() {
        return nrDoors;
    }
}


