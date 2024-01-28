import java.awt.*;

public class Scania extends Car{
    private double truckBedAngle;

    public Scania() {
        super(2, Color.black, 200, "Scania", 0, 0, 0);
        this.truckBedAngle = 0;
        stopEngine();
    }
    protected void setTruckBedAngle(double angle) {
        if (getCurrentSpeed() != 0) {
            throw new IllegalCallerException("Scania is currently moving, stop before altering truck bed angle");
        } else {
            if (angle < 0 || 70 < angle) {
                throw new IllegalArgumentException("Invalid input, The angle of the Truck Bed should be in [0, 70]");
            } else {
                this.truckBedAngle = angle;
            }
        }
    }
    protected double getTruckBedAngle() {
        return this.truckBedAngle;
    }
    @Override
    protected void startEngine(){
        if (getTruckBedAngle() != 0) {
            throw new IllegalCallerException("The truck bed is raised, This object will not move");
        }
        else {
            currentSpeed = 0.1;
        }
    }

   @Override
   protected double speedFactor() {
        return getEnginePower() * 0.005;
    }
}
