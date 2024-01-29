import java.awt.*;

public class Scania extends Truck{
    private double truckBedAngle;
    private boolean rampRaised;
    private int nrCarsLoaded;
    public Scania() {
        super(2, Color.black, 200, "Scania");
        this.truckBedAngle = 0;
        this.rampRaised = true;
        this.nrCarsLoaded = 0;
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
                    setCanMove(true);
                }
                else {
                    setCanMove(false);
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
