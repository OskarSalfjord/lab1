import java.awt.*;
import java.util.Stack;

public class Cartransport extends Car {
    public boolean RampUp;
    private Stack<Car> carRamp = new Stack<Car>();
    public Cartransport() {
        super(2, Color.darkGray, 500, "Cartransport", 0, 0, 0);
        RampUp = false;
        this.carRamp.setSize(2);
    }
    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.08 / (carRamp.capacity() + 1);
    }
}
