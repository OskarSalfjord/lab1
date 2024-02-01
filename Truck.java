import java.awt.*;

public abstract class Truck extends Vehicle {
    private boolean CanMove;
    private final int nrDoors;
    public Truck(int nrDoors, Color color, double enginePower, String modelName, double weight, double x, double y, double direction) {
        super(color, enginePower, modelName, weight, x, y, direction);
        this.nrDoors = nrDoors;
    }
}
