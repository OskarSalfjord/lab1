import java.awt.*;
import java.util.Stack;

public abstract class Cartransport extends Truck {
    private enum Ramp {
        RAISED, LOWERED
    }

    private Stack<Car> carRamp = new Stack<>();
    private Ramp currentRampState;

    public Cartransport() {
        super(2, Color.darkGray, 500, "Cartransporter3000");
        this.currentRampState = Ramp.RAISED;
    }

    protected Ramp getRamp() {
        return this.currentRampState;
    }

    protected void raiseRamp() {
        this.currentRampState = Ramp.RAISED;
    }

    protected void lowerRamp() {
        this.currentRampState = Ramp.LOWERED;
    }
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
