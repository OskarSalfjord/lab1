import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Saab95 Saab;
    private Saab95 Saab2;
    private Volvo240 Volvo;
    private Scania Scania;
    private Cartransporter3000 Cartransporter;
    private List<Car> testList;

    @BeforeEach
    void setUp() {
        Volvo = new Volvo240(Color.black, 0, 0, 0);
        Saab = new Saab95(Color.red, 0, 0, 0);
        Saab2 = new Saab95(Color.CYAN, 0.5, 0, 0);
        Scania = new Scania(Color.lightGray, -0.5, 0 , 0);
        Cartransporter = new Cartransporter3000(Color.orange, 0, 0, 0);
    }
    @Test
    void getNrDoors() {
        assertEquals(2, Saab.getNrDoors());
        assertEquals(4, Volvo.getNrDoors());
    }
    @Test
    void getEnginePower() {
        assertEquals(125, Saab.getEnginePower());
        assertEquals(100, Volvo.getEnginePower());
    }
    @Test
    void getCurrentSpeed() {
        assertEquals(0, Saab.getCurrentSpeed());
        assertEquals(0, Volvo.getCurrentSpeed());
    }
    @Test
    void getColor() {
        assertEquals(Color.red, Saab.getColor());
        assertEquals(Color.black, Volvo.getColor());
    }
    @Test
    void setColor() {
        Saab.setColor(Color.blue);
        assertEquals(Color.blue, Saab.getColor());

        Volvo.setColor(Color.pink);
        assertEquals(Color.pink, Volvo.getColor());
    }
    @Test
    void startEngine() {
        Saab.startEngine();
        assertTrue(Saab.getEngineOn());

        Volvo.startEngine();
        assertTrue(Volvo.getEngineOn());
        }
    @Test
    void stopEngine() {
        Saab.startEngine();
        Saab.stopEngine();
        assertEquals(0, Saab.getCurrentSpeed());

        Volvo.startEngine();
        Volvo.stopEngine();
        assertFalse(Volvo.getEngineOn());
    }
    @Test
    void turnLeft() {
        Saab.turnLeft();
        assertEquals(Math.PI/2, Saab.getDirection());

        Volvo.turnLeft();
        assertEquals(Math.PI/2, Volvo.getDirection());
    }
    @Test
    void turnRight() {
        Saab.turnRight();
        assertEquals(-Math.PI/2, Saab.getDirection());

        Volvo.turnRight();
        assertEquals(-Math.PI/2, Volvo.getDirection());
    }
    @Test
    void move() {
        Saab.startEngine();
        Saab.gas(0.1);
        Saab.move();
        assertTrue(Saab.getX() > 0);

        Saab.turnRight();
        Saab.move();
        assertEquals(-0.125, Saab.getY());
        assertEquals(0.125, Saab.getX());

        Volvo.startEngine();
        Volvo.gas(0.1);
        Volvo.move();
        assertEquals(0.125, Volvo.getX());

        Volvo.turnLeft();
        Volvo.move();
        assertEquals(0.125, Volvo.getY());
        assertEquals(0.125, Volvo.getX());
    }
    @Test
    void gasAndBreak() {
        Saab.startEngine();
        Saab.gas(0.5);
        Saab.brake(0.3);
        assertTrue(Saab.getCurrentSpeed() >= 0 && Saab.getCurrentSpeed() <= Saab.getEnginePower());
    }

    @Test
    void gasAndBreakNegative() {
        Saab.startEngine();
        assertThrows(IllegalArgumentException.class, () -> Saab.gas(-100));
        assertThrows(IllegalArgumentException.class, () -> Saab.brake(-50));
    }
    @Test
    void gasWithEngineTurnedOff() {
        assertThrows(IllegalCallerException.class, () -> Saab.gas(0.5));
    }

    @Test
    void SaabTurbo() {
        double speedFactorOrg = Saab.speedFactor();
        Saab.setTurboOn();
        double speedFactorTurbo = Saab.speedFactor();
        Saab.setTurboOff();
        double speedFactorNoTurbo = Saab.speedFactor();
        assertTrue(speedFactorOrg == speedFactorNoTurbo && speedFactorOrg*1.3 == speedFactorTurbo);
    }
    @Test
    void VolvoSF() {
        Volvo.startEngine();
        assertEquals(1.25, Volvo.speedFactor());
    }
    @Test
    void raisingRamp() {
        Scania.raiseRamp();
        assertEquals(5, Scania.getRampAngle());
    }
    @Test
    void changingAngleWhileMoving() {
        Scania.startEngine();
        Scania.gas(0.3);
        assertThrows(IllegalStateException.class, () -> Scania.raiseRamp());
    }
    @Test
    void MovingWhileTruckBedIsRaised() {
        Scania.raiseRamp();
        Scania.startEngine();
        assertThrows(IllegalStateException.class, () -> Scania.gas(0.0000001));
    }
    @Test
    void LowerRampWhileStandingStill() {
        Cartransporter.lowerRamp();
        assertEquals(Cartransporter.getRampStatus(), RampC.RampEnum.LOWERED);
    }
    @Test
    void LowerRampWhileMoving() {
        Cartransporter.raiseRamp();
        Cartransporter.startEngine();
        Cartransporter.gas(1);
        assertThrows(IllegalCallerException.class, () -> Cartransporter.lowerRamp());
    }
    @Test
    void LoadingCar() { // Saab is sufficiently close to cartransporter as default (both has pos 0,0)
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        assertTrue(Cartransporter.loadedCars.contains(Volvo));
    }
    @Test
    void LoadingCarWhileRampIsRaised() { // Same as LoadingCars(), but now Ramp is raised (default value)
        assertThrows(IllegalCallerException.class, () -> Cartransporter.loadCar(Saab));
    }
    @Test
    void LoadingCarWhileNotSufficientlyClose() { // Now the Saab *should* be too far away for loading
        Saab.startEngine();
        Saab.gas(1);
        Saab.move();
        Cartransporter.lowerRamp();
        assertThrows(IllegalCallerException.class, () -> Cartransporter.loadCar(Saab));
    }
    @Test
    void LoadingTooManyCars() { // Maximum no. of cars is set to 2
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Saab);
        Cartransporter.loadCar(Volvo);
        assertThrows(IllegalCallerException.class, () -> Cartransporter.loadCar(Saab2));
    }
    @Test
    void CheckPosOfLoadedCars() { // Problem här
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.raiseRamp();
        Cartransporter.startEngine();
        Cartransporter.gas(1);
        Cartransporter.move();
        assertTrue(Cartransporter.getX() != 0 && Cartransporter.getX() == Cartransporter.loadedCars.peek().getX() && Cartransporter.getY() == Cartransporter.loadedCars.peek().getY());
    }
    @Test
     void UnloadCar() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.unLoadCar();
        assertThrows(EmptyStackException.class, () -> Cartransporter.loadedCars.peek());
    }
    @Test
    void CheckUnloadedCarPos() { // Samma problem här
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.raiseRamp();
        Cartransporter.startEngine();
        Cartransporter.gas(0.3);
        Cartransporter.move();
        Cartransporter.stopEngine();
        Cartransporter.lowerRamp();
        Cartransporter.unLoadCar();
        assertTrue(Math.sqrt(Math.pow(Volvo.getX()-Cartransporter.getX(), 2) + Math.pow(Volvo.getY() - Cartransporter.getY(), 2)) <= 1);
    }
    @Test
    void UnloadCarWhileRampIsRaised() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.raiseRamp();
        assertThrows(IllegalCallerException.class, () -> Cartransporter.unLoadCar());
    }
    @Test
    void FILOtest() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.loadCar(Saab);
        Cartransporter.unLoadCar();
        assertTrue(Cartransporter.loadedCars.size() == 1 && Cartransporter.loadedCars.contains(Volvo));
    }
    @Test
    void TurningLeftWithLoad() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.raiseRamp();
        Cartransporter.turnLeft();
        Cartransporter.startEngine();
        Cartransporter.gas(0.03);
        assertTrue(Cartransporter.getX() == 0 && Volvo.getX() == 0);
    }
    @Test
    void TurningRightWithLoad() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.raiseRamp();
        Cartransporter.turnRight();
        Cartransporter.startEngine();
        Cartransporter.gas(0.03);
        assertTrue(Cartransporter.getX() == 0 && Volvo.getX() == 0);
    }
    @Test
    void BrakeWithLoad() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        Cartransporter.raiseRamp();
        Cartransporter.startEngine();
        Cartransporter.gas(0.1);
        assertFalse(Cartransporter.getCurrentSpeed() == 0 && Cartransporter.loadedCars.peek().getCurrentSpeed() == 0);
        Cartransporter.brake(1);
        assertTrue(Cartransporter.getCurrentSpeed() == 0 && Cartransporter.loadedCars.peek().getCurrentSpeed() == 0);
    }
    @Test
    void ScaniaRaiseLowerRamp() {
        Scania.raiseRamp();
        assertEquals(Scania.getRampAngle(), 5);
        Scania.lowerRamp();
        assertEquals(Scania.getRampAngle(), 0);
    }
    @Test
    void CartransportCapacity() {
        assertEquals(Cartransporter.getCapacity(), 2);
    }
    @Test
    void CartransportContents() {
        Cartransporter.lowerRamp();
        Cartransporter.loadCar(Volvo);
        testList = new ArrayList<>();
        testList.add(Volvo);

        assertEquals(Cartransporter.getCarsInLoad(), testList);
    }

    @Test
    void getSpeedFactor() {
        assertEquals(Volvo.getSpeedFactor(), 1.25);
    }
    @Test
    void getModelName() {
        assertEquals(Volvo.getModelName(), "Volvo240");
    }


}