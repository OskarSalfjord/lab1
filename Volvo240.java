import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        nrDoors = 4;
        color = Color.black;
        enginePower = 100;
        modelName = "Volvo240";
        x = 0;
        y = 0;
        direction = 0;
        stopEngine();
    }

    @Override
    double speedFactor() {
        return enginePower * 0.01 * trimFactor;
    }
}
