import java.awt.*;

public class Scania extends Truck{
    private double truckBedAngle;

    public Scania(Color color, double x, double y, double direction) {
        super(2,color, 200, "Scania", 5000, x, y, direction);
        this.truckBedAngle = 0;
        stopEngine();
    }
    protected void setTruckBedAngle(double angle) {
        if (getCurrentSpeed() != 0) {
            throw new IllegalCallerException("This truck is currently moving, stop before altering truck bed angle");
        } else {
            if (angle < 0 || 70 < angle) {
                throw new IllegalArgumentException("Invalid input, The angle of the truck bed should be in the range [0, 70]");
            } else {
                if (angle == 0) {
                    setWorking(false);
                }
                else {
                    setWorking(true);
                }
                this.truckBedAngle = angle;
            }
        }
    }

    protected double getTruckBedAngle() {
        return this.truckBedAngle;
    }
   @Override
   protected double speedFactor() {
        return getEnginePower() * 0.005;
    }
}
