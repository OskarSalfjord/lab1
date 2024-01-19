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
        direction = direction - 90;
    }
    @Override
    public void turnRight() {
        direction = direction + 90;
    }
    @Override
    public void move() {
        x = x+getCurrentSpeed()/Math.sin(direction);
        y = y+getCurrentSpeed()/Math.cos(direction);
    }
}
