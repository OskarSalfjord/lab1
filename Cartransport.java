import java.awt.*;
import java.util.Stack;

public class Cartransport extends Truck {
    private boolean CanBeLoaded;
    private Stack<Car> carRamp = new Stack<Car>();
    public Cartransport() {
        super(2, Color.darkGray, 500, "Cartransporter3000");
        CanBeLoaded = false;
    }
    protected boolean getCanBeLoaded(){
        return this.CanBeLoaded;
    }
    protected void setCanBeLoaded(boolean status) {
        this.CanBeLoaded = status;
    }
    protected void loadCar(Car carToLoad) {
        if (carToLoad.getLoadable()) {
            carRamp.add(carToLoad);
        }
        else {
            throw new IllegalArgumentException("This car can not be loaded onto the ramp");
        }

    }

    @Override
    protected void startEngine(){
        if (getCanBeLoaded()) {
            throw new IllegalCallerException("The ramp is currently down, Close it before starting the engine");
        }
        else {
            currentSpeed = 0.1;
        }
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.08 / (carRamp.capacity() + 1);
    }
}
