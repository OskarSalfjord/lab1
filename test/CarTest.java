import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Saab95 Saab;
    private Volvo240 Volvo;
    @BeforeEach
    void setUp() {
        Volvo = new Volvo240();
        Saab = new Saab95();
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
  // fråga ensälla du
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
        assertEquals(0.1, Saab.currentSpeed);

        Volvo.startEngine();
        assertEquals(0.1, Volvo.currentSpeed);

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
        assertEquals(Math.PI/2, Saab.direction);

        Volvo.turnLeft();
        assertEquals(Math.PI/2, Volvo.direction);

    }

    @Test
    void turnRight() {
        Saab.turnRight();
        assertEquals(-Math.PI/2, Saab.direction);

        Volvo.turnRight();
        assertEquals(-Math.PI/2, Volvo.direction);
    }

    @Test
    void move() {
        Saab.startEngine();
        Saab.move();
        assertEquals(0.1, Saab.x);

        Saab.turnRight();
        Saab.move();
        assertEquals(-0.1, Saab.y);
        assertEquals(0.1, Saab.x);

        Volvo.startEngine();
        Volvo.move();
        assertEquals(0.1, Volvo.x);

        Volvo.turnLeft();
        Volvo.move();
        assertEquals(0.1, Volvo.y);
        assertEquals(0.1, Volvo.x);
    }
}