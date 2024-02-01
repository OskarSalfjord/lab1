import java.awt.*;

public class Scania extends Truck implements Ramp{
    private int RampAngle;
    public Scania(Color color, double x, double y, double direction) {
        super(2, color, 200, "Scania", 5000, x, y, direction);
        this.RampAngle = 0;
    }
    protected int getRampAngle() {
        return this.RampAngle;
    }
   @Override
   protected double speedFactor() {
        return getEnginePower() * 0.005;
    }
    @Override
    public void raiseRamp() { // Raises the Ramp with 5 degrees
        if (getEngineOn()) {
            throw new IllegalStateException("Turn engine off before trying to raise the ramp");
        }
        else {
            setRampAngle(Math.min(getRampAngle() + 5, 70));
        }
    }
    @Override
    public void lowerRamp() { // Lowers the ramp with 5 degrees
        setRampAngle(Math.max(getRampAngle() - 5, 0));
    }
    private void setRampAngle(int angle) {
        RampAngle = angle;
    }
    @Override
    protected void gas(double amount) {
        if (RampAngle == 0) {
            super.gas(amount);
        }
        else
            throw new IllegalStateException("Lower Ramp to 0 degrees before trying to move");
    }
}
