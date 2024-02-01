import java.awt.*;

public class Scania extends Truck{
    private double RampAngle;

    public Scania(Color color, double x, double y, double direction) {
        super(2,color, 200, "Scania", 5000, x, y, direction);
        this.RampAngle = 0;
        stopEngine();
    }
    protected double getTruckBedAngle() {
        return this.RampAngle;
    }
   @Override
   protected double speedFactor() {
        return getEnginePower() * 0.005;
    }
    @Override
    protected void raiseRamp() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalCallerException("This truck is currently moving, stop before altering truck bed angle");
        }
        else {
            if (70 > RampAngle) {
                RampAngle += 1;
                setCanMove(true);
            }
        }
    }
    @Override
    protected void lowerRamp() {
        if (RampAngle > 0) {
            RampAngle -= 1;
            if (RampAngle == 0) {
                setCanMove(false);
            }
        }
    }
}
