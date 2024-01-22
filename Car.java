import java.awt.*;
public abstract class Car implements Movable{
    public int nrDoors; // Number of doors on the car
    public double enginePower; // Engine power of the car
    public double currentSpeed; // The current speed of the car
    public Color color; // Color of the car
    public String modelName; // The car model name

    public double x; // x coordinate
    public double y; // y coordinate
    public double direction;// direction in degrees

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    @Override
    public void turnLeft() {
        direction = direction + Math.PI/2;
    }
    @Override
    public void turnRight() {
        direction = direction - Math.PI/2;
    }
    @Override
    public void move() {
        y = y+getCurrentSpeed()*Math.sin(direction);
        x = x+getCurrentSpeed()*Math.cos(direction);
    }
    double speedFactor(){
        return 0;
    }
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() + speedFactor() * amount, 0);
    }
    public void gas(double amount){
        incrementSpeed(amount);
    }
    public void brake(double amount){
        decrementSpeed(amount);
    }
}
