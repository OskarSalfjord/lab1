import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AutoShopTest {
    private Volvo240 volvo1;
    private Saab95 saab1;
    private Volvo240 volvo2;
    private AutoShop<Car> AllCars;
    private AutoShop<Volvo240> VolvoShop;
    private List<Car> AllCarsList;
    private List<Volvo240> VolvoList;

    @BeforeEach
    void setUp() {
        volvo1 = new Volvo240(Color.BLACK, 0, 0, 0);
        volvo2 = new Volvo240(Color.BLACK, 0, 0, 0);
        saab1 = new Saab95(Color.red, 0, 0, 0);

        AllCarsList = new ArrayList<>();
        AllCarsList.add(volvo1);
        AllCarsList.add(saab1);

        VolvoList = new ArrayList<>();
        VolvoList.add(volvo1);

        AllCars = new AutoShop<>(5, AllCarsList);
        VolvoShop = new AutoShop<>(2, VolvoList);

    }
    @Test
    void getCarsInShop() {
        List<Car> CheckTestList = new ArrayList<>();
        CheckTestList.add(volvo1);
        CheckTestList.add(saab1);

        assertEquals(AllCars.getCarsInShop(), CheckTestList);
    }

    @Test
    void loadCar() {
        AllCars.loadCar(volvo2);

        List<Car> CheckTestList = new ArrayList<>();
        CheckTestList.add(volvo1);
        CheckTestList.add(saab1);
        CheckTestList.add(volvo2);

        assertEquals(AllCars.getCarsInShop(), CheckTestList);

        //VolvoShop.loadCar(saab1);
    }

    @Test
    void unLoadCar() {
        AllCars.unLoadCar(saab1);
        List<Car> CheckTestList = new ArrayList<>();
        CheckTestList.add(volvo1);
        assertEquals(AllCars.getCarsInShop(), CheckTestList);
    }
}