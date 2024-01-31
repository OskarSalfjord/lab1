
import java.util.ArrayList;
import java.util.List;

public class AutoShop <T extends Car> implements Loadable<T> {

    private int capacity;
    private List<T> carsInShop;

    public AutoShop(int capacity, List<T> carsInShop) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.carsInShop = carsInShop;
    }

    private void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
    }
    @Override
    public void loadCar(T carToLoad) {
        if (carsInShop.size() < capacity) {
            {carsInShop.add(carToLoad);}}
        else{throw new IllegalArgumentException("Shop is full");}
    }
    @Override
    public void unLoadCar (T carToUnload){
        carsInShop.remove(carToUnload);
    }

    public static void main (String[]args){
        Volvo240 volvo1 = new Volvo240();
        Saab95 saab1 = new Saab95();
        Saab95 saab2 = new Saab95();

        List<Saab95> cars = new ArrayList<>();
        cars.add(saab1);

        AutoShop<Saab95> autoshop = new AutoShop<>(5, cars);
        autoshop.loadCar(saab2);
        autoshop.unLoadCar(saab1);
        autoshop.loadCar(volvo1);
        autoshop.unLoadCar(volvo1);

    }
}

