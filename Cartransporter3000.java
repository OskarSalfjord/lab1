import java.awt.*;
import java.util.List;
import java.util.Stack;

public class Cartransporter3000 extends Truck implements Loadable<Car>, Ramp{
    protected Stack<Car> loadedCars = new Stack<>();
    protected RampC<RampC.RampEnum> transporterRamp;
    private final int capacity;
    private final double maxWeight;
    public Cartransporter3000(Color color, double x, double y, double direction) {
        super(2, color, 500, "Cartransporter3000", 3000, x, y, direction);
        this.capacity = 2;
        this.maxWeight = 1500;
        transporterRamp = new RampC<>(RampC.RampEnum.RAISED);
    }

    protected RampC.RampEnum getRampStatus() {return transporterRamp.getRampStatus();}
    @Override
    public void raiseRamp() {transporterRamp.setRampStatus(RampC.RampEnum.RAISED);}
    @Override
    public void lowerRamp() {
        if(getEngineOn()) {
            throw new IllegalCallerException("The car transporters engine is on, ramp cannot be lowered");
        }
        else {transporterRamp.setRampStatus(RampC.RampEnum.LOWERED);}
    }
    @Override
    public void loadCar(Car carToLoad) {
        if (carToLoad.getWeight() <= this.maxWeight) {
            if (Math.sqrt(Math.pow(carToLoad.getX() - this.getX(), 2) + Math.pow(carToLoad.getY() - this.getY(), 2)) <= 1) {
                if (getRampStatus() == RampC.RampEnum.LOWERED) {
                    if (loadedCars.size() < capacity) {
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
    @Override
    public int getCapacity() {return capacity;}
    @Override
    public List<Car> getCarsInLoad() {return loadedCars;}
    public void unLoadCar() {
        if (getRampStatus() == RampC.RampEnum.LOWERED) {
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
    public void move() {
        super.move();
        for (Car car : loadedCars) {
            car.setPosition(getX(), getY());
        }
    }
    @Override
    protected void gas(double amount) {
        if (getRampStatus() == RampC.RampEnum.LOWERED) {
            throw new IllegalStateException("Raise ramp before you try to move");
        }
        else {
            super.gas(amount);
        }
    }
}
