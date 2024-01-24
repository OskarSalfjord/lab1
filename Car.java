import java.awt.*;
public abstract class Car implements Movable{
    private int nrDoors;
    private Color color;
    private double enginePower;
    private String modelName;
    private double currentSpeed;
    private double x;// x coordinate
    private double y; // y coordinate
    private double direction;// direction in radians

    public Car(int nrDoors, Color color, double enginePower, String modelName, double x, double y, double direction) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getDirection() {
        return direction;
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
    protected abstract double speedFactor();
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    public void gas(double amount){
        if (0 <= amount && amount <= 1)
            incrementSpeed(amount);
    }
    public void brake(double amount){
        if (0 <= amount && amount <= 1)
            decrementSpeed(amount);
    }
}
