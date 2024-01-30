import java.awt.*;
import java.util.Stack;

public class Cartransporter3000 extends Truck {
    private enum Ramp {
        RAISED, LOWERED
    }

    private Stack<Car> loadedCars = new Stack<>();
    private Ramp currentRampState;

    private final int maximumLoad;

    private double maxWeight;

    public Cartransporter3000() {
        super(2, Color.darkGray, 500, "Cartransporter3000", 3000);
        this.maximumLoad = 4;
        this.currentRampState = Ramp.RAISED;
        this.maxWeight = 1500;
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
        if (carToLoad.getWeight() <= this.maxWeight) {
            if (Math.sqrt(Math.pow(carToLoad.getX() - this.getX(), 2) + Math.pow(carToLoad.getY() - this.getY(), 2)) <= 1) {
                if (getRamp() == Ramp.LOWERED) {
                    if (loadedCars.size() < maximumLoad) {
                        loadedCars.add(carToLoad);
                    } else {
                        throw new IllegalArgumentException("The car transport is full");
                    }
                } else {
                    throw new IllegalArgumentException("The ramp is raised, car can not be loaded");
                }
            } else{
                throw new IllegalCallerException("The car is too far away from the transporter");
            }
        } else {
            throw new IllegalArgumentException("The car is not loadable");
        }
    }
    protected void unLoadCar() {
        if (getRamp() == Ramp.LOWERED) {
            loadedCars.pop();
        } else {
            throw new IllegalArgumentException("The ramp is raised, car can not be unloaded");
        }
    }
    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.08 / (maximumLoad + 1);
    }
    @Override
    public void turnLeft() {
        this.turnLeft();
        for (Car car: loadedCars) {
            car.turnLeft();
        }
    }
    @Override
    public void turnRight() {
        this.turnRight();
        for (Car car: loadedCars) {
            car.turnRight();
        }
    }
    @Override
    public void move() {
        this.move();
        for (Car car: loadedCars) {
            car.move();
        }
    }
}
