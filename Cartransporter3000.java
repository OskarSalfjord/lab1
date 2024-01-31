import java.awt.*;
import java.util.Stack;

public class Cartransporter3000 extends Truck {
    protected enum Ramp {
        RAISED, LOWERED
    }

    protected Stack<Car> loadedCars = new Stack<>();
    private Ramp currentRampState;

    private final int maximumLoad;

    private double maxWeight;

    public Cartransporter3000() {
        super(2, Color.darkGray, 500, "Cartransporter3000", 3000);
        this.maximumLoad = 2;
        this.currentRampState = Ramp.RAISED;
        this.maxWeight = 1500;
        this.setCanMove(true);
    }

    protected Ramp getRamp() {
        return this.currentRampState;
    }

    protected void raiseRamp() {
        this.currentRampState = Ramp.RAISED;
        this.setCanMove(true);
    }

    protected void lowerRamp() {
        if(currentSpeed == 0) {
            this.currentRampState = Ramp.LOWERED;
            this.setCanMove(false);
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
                        throw new IllegalCallerException("The car transport is full");
                    }
                } else {
                    throw new IllegalCallerException("The ramp is raised, car can not be loaded");
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
            throw new IllegalCallerException("The ramp is raised, car can not be unloaded");
        }
    }
    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.08 / (loadedCars.size() + 1);
    }

    protected void turnLeft(int NoOfItemsInStack) {
        this.turnLeft();
        int i = 0;
        while (i < NoOfItemsInStack) {
            loadedCars.elementAt(i).turnLeft();
            i++;
        }
    }
    protected void turnRight(int NoOfItemsInStack) {
        this.turnRight();
        int i = 0;
        while (i < NoOfItemsInStack) {
            loadedCars.elementAt(i).turnRight();
            i++;
        }
    }
    protected void move(int NoOfItemsInStack) {
        this.move();
        int i = 0;
        while (i < NoOfItemsInStack) {
            loadedCars.elementAt(i).move();
            i++;
        }
    }
    protected void gas(double amount, int NoOfItemsInStack) {
        this.gas(amount);
        int i = 0;
        while (i < NoOfItemsInStack) {
            loadedCars.elementAt(i).gas(amount);
            i++;
        }
    }
    protected void brake(double amount, int NoOfItemsInStack) {
        this.brake(amount);
        int i = 0;
        while (i < NoOfItemsInStack) {
            loadedCars.elementAt(i).brake(amount);
        }
    }
}
