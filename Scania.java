import java.awt.*;

public class Scania extends Car{
    private double truckBedAngle;

    public Scania() {
        super(2, Color.black, 200, "Scania", 0, 0, 0, true  );
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
            throw new IllegalCallerException("The truck bed is raised, This truck will not move");
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
