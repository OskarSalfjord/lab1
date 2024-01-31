import java.awt.*;
import java.util.Stack;

public class Cartransporter3000 extends Truck implements Loadable{
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
    @Override
    public void loadCar(Car carToLoad) {
        if (carToLoad.getWeight() <= this.maxWeight) {
            if (Math.sqrt(Math.pow(carToLoad.getX() - this.getX(), 2) + Math.pow(carToLoad.getY() - this.getY(), 2)) <= 1) {
                if (getRamp() == Ramp.LOWERED) {
                    if (loadedCars.size() < maximumLoad) {
                        loadedCars.add(carToLoad);
                        carToLoad.setPosition(this.getX(), this.getY());
                        carToLoad.setDirection(getDirection());
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

    public void unLoadCar() {
        if (getRamp() == Ramp.LOWERED) {
            Car UnloadedCar = loadedCars.pop();
            UnloadedCar.setPosition(getX(), getY() - 0.5);

        } else {
            throw new IllegalCallerException("The ramp is raised, car can not be unloaded");
        }
    }
    @Override
    protected double speedFactor() {return getEnginePower() * 0.002 * (0.9 / (loadedCars.size() + 1));
    }
    @Override
    protected void startEngine() {
        super.startEngine();
        for (Car car : loadedCars) {
            car.startEngine();
        }
    }
    @Override
    protected void stopEngine() {
        super.stopEngine();
        for (Car car : loadedCars) {
            car.stopEngine();
        }
    }
    @Override
    public void turnLeft() {
        super.turnLeft();
        for (Car car : loadedCars) {
            car.turnLeft();
        }
    }
    @Override
    public void turnRight() {
        super.turnRight();
        for (Car car : loadedCars) {
            car.turnRight();
        }
    }
    @Override
    public void move() {
        super.move();
        for (Car car : loadedCars) {
            car.setPosition(getX(), getY());
        }
    }
    @Override
    protected void gas(double amount) {
        super.gas(amount);
        for (Car car : loadedCars) {
            double speedfactorchange = (speedFactor() / car.getSpeedFactor());
            car.gas(amount * speedfactorchange);
        }
    }
    @Override
    protected void brake(double amount) {
        super.brake(amount);
        for (Car car : loadedCars) {
            double speedfactorchange = (speedFactor() / car.getSpeedFactor());
            car.brake(amount * speedfactorchange);
        }
    }
}
