import java.awt.*;
import java.util.Stack;

public abstract class Cartransporter3000 extends Truck {
    private enum Ramp {
        RAISED, LOWERED
    }

    private Stack<Car> loadedCars = new Stack<>();
    private Ramp currentRampState;

    private int maximumLoad;

    public Cartransporter3000() {
        super(2, Color.darkGray, 500, "Cartransporter3000");
        this.maximumLoad = 4;
        this.currentRampState = Ramp.RAISED;
    }

    protected Ramp getRamp() {
        return this.currentRampState;
    }

    protected void raiseRamp() {
        this.currentRampState = Ramp.RAISED;
    }

    protected void lowerRamp() {
        if(currentSpeed == 0) {
            this.currentRampState = Ramp.LOWERED;
        }
        else{
            throw new IllegalCallerException("The car transport is moving, ramp cannot be lowered");
        }
    }

    protected void loadCar(Car carToLoad) {
        if (carToLoad.getLoadable()) {
            if (getRamp() == Ramp.LOWERED) {
                if (loadedCars.size() < maximumLoad) {
                    loadedCars.add(carToLoad);

                } else {
                    throw new IllegalArgumentException("The car transport is full");
                }
            } else {
                throw new IllegalArgumentException("The ramp is raised, car can not be loaded");
            }
        }
        else {
                throw new IllegalArgumentException("The car is not loadable");
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.08 / (carRamp.capacity() + 1);
    }
}
