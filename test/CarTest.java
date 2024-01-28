import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Saab95 Saab;
    private Volvo240 Volvo;
    private Scania Scania;
    @BeforeEach
    void setUp() {
        Volvo = new Volvo240();
        Saab = new Saab95();
        Scania = new Scania();
    }

    @AfterEach
    void tearDown() {
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
        assertEquals(0.1, Saab.getCurrentSpeed());

        Volvo.startEngine();
        assertEquals(0.1, Volvo.getCurrentSpeed());

    }

    @Test
    void stopEngine() {
        Saab.startEngine();
        Saab.stopEngine();
        assertEquals(0, Saab.getCurrentSpeed());

        Volvo.startEngine();
        Volvo.stopEngine();
        assertEquals(0, Volvo.getCurrentSpeed());
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
        Saab.move();
        assertEquals(0.1, Saab.getX());

        Saab.turnRight();
        Saab.move();
        assertEquals(-0.1, Saab.getY());
        assertEquals(0.1, Saab.getX());

        Volvo.startEngine();
        Volvo.move();
        assertEquals(0.1, Volvo.getX());

        Volvo.turnLeft();
        Volvo.move();
        assertEquals(0.1, Volvo.getY());
        assertEquals(0.1, Volvo.getX());
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
        assertThrows(IllegalArgumentException.class, () -> Saab.gas(-100));
        assertThrows(IllegalArgumentException.class, () -> Saab.brake(-50));
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
        assertEquals(0.00125, Volvo.speedFactor());
    }
    @Test
    void setTruckBedAngleNegative() {
        assertThrows(IllegalArgumentException.class, () -> Scania.setTruckBedAngle(-69));
        assertThrows(IllegalArgumentException.class, () -> Scania.setTruckBedAngle(1337));
    }
    @Test
    void changingAngleWhileMoving() {
        Scania.startEngine();
        assertThrows(IllegalCallerException.class, () -> Scania.setTruckBedAngle(5));
    }
    @Test
    void MovingWhileTruckBedIsRaised() {
        Scania.setTruckBedAngle(69);
        assertThrows(IllegalCallerException.class, () -> Scania.startEngine());
    }


}