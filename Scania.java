import java.awt.*;

public class Scania extends Truck implements Ramp{
    private int RampAngle;

    public Scania(Color color, double x, double y, double direction) {
        super(2,color, 200, "Scania", 5000, x, y, direction);
        this.RampAngle = 0;
        stopEngine();
        setCanMove(true);
    }
    protected int getRampAngle() {
        return this.RampAngle;
    }
   @Override
   protected double speedFactor() {
        return getEnginePower() * 0.005;
    }
    @Override
    public void raiseRamp() {
        if (getCurrentSpeed() != 0) {
            throw new IllegalCallerException("This truck is currently moving, stop before altering truck bed angle");
        }
        else {
            if (70 > RampAngle) {
                RampAngle += 1;
                setCanMove(false);
            }
        }
    }
    @Override
    public void lowerRamp() {
        if (RampAngle > 0) {
            RampAngle -= 1;
            if (RampAngle == 0) {
                setCanMove(true);
            }
        }
    }
    protected void setRampAngle(int angle) {
        int DistanceFromCurrAngle = angle - getRampAngle();
        int i = 0;
        if (DistanceFromCurrAngle > 0) {
            while (i < DistanceFromCurrAngle) {
                raiseRamp();
                i++;
            }
        }
        else {
            while (i < Math.abs(DistanceFromCurrAngle)) {
                lowerRamp();
                i++;
            }
        }
    }
}
