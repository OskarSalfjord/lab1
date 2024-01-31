import java.awt.*;

public class Saab95 extends Car{

    private boolean turboOn;
    
    public Saab95(Color color, double x, double y, double direction){
        super(2, color, 125, "Saab95", 1000,x, y, direction);
	    turboOn = false;
    }

    protected void setTurboOn(){
	    turboOn = true;
    }

    protected void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
