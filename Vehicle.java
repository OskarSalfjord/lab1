import java.awt.*;
public abstract class Vehicle implements Movable{
    //FIXA FLAK INTERFACE?
    private Color color;
    private final double enginePower;
    private String modelName;
    private final double weight;
    protected double currentSpeed;
    private double x;// x coordinate
    private double y; // y coordinate
    private double direction;// direction in radians
    private boolean engineOn;

    public Vehicle(Color color, double enginePower, String modelName, double weight, double x, double y, double direction) {
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.weight = weight;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.engineOn = false;
    }
    protected boolean getEngineOn() {
        return this.engineOn;
    }
    protected double getEnginePower(){
        return enginePower;
    }

    protected String getModelName(){
        return modelName;
    }

    protected double getWeight() {
        return weight;
    }

    protected double getCurrentSpeed(){
        return currentSpeed;
    }
    protected double getX() {
        return x;
    }
    protected double getY() {
        return y;
    }
    protected double getDirection() {
        return direction;
    }
    protected void setDirection(double NewDirection) {
        this.direction = NewDirection;
    }
    protected Color getColor(){
        return color;
    }
    protected void setColor(Color clr){
        color = clr;
    }

    protected void startEngine(){
        this.engineOn = true;
    }

    protected void stopEngine(){
        this.currentSpeed = 0;
        this.engineOn = false;
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
    protected double getSpeedFactor() {
        return this.speedFactor();
    }
    protected void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }
    protected void gas(double amount) {
        if (engineOn) {
            if (amount < 0 || 1 < amount) {
                throw new IllegalArgumentException("Invalid input, valid input between [0, 1]");
            }
            else {
                incrementSpeed(amount);
            }
        }
        else {
            throw new IllegalCallerException("Turn engine on before trying to move");
        }
    }
    protected void brake(double amount){
        if (amount < 0 || 1 < amount) {
            throw new IllegalArgumentException("Invalid input, valid input between [0, 1]");
        }
        else {
            decrementSpeed(amount);
        }
    }
}
