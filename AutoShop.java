
import java.util.ArrayList;
import java.util.List;

public class AutoShop <T> implements Loadable<T>{
    protected final int capacity;
    protected List<T> carsInShop;

    public AutoShop(int capacity, List<T> carsInShop) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.carsInShop = carsInShop;
    }
    protected List<T> getCarsInShop() {return carsInShop; }

    @Override
    public void loadCar(T carToLoad) {
        if (carsInShop.size() < capacity) {
            {carsInShop.add(carToLoad);}}
        else{throw new IllegalArgumentException("Shop is full");}
    }

    protected void unLoadCar(T carToUnload){
        carsInShop.remove(carToUnload);
    }

}

