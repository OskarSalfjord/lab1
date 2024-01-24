import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        super(4, Color.black, 100, "Volvo240", 0, 0, 0);
        stopEngine();
    }
    @Override
    public double speedFactor() {
        return getCurrentSpeed() * 0.01 * trimFactor;
    }
}
