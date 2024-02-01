
import java.util.List;

public class AutoShop <T> implements Loadable<T>{
    public final int capacity;
    protected List<T> carsInShop;

    public AutoShop(int capacity, List<T> carsInShop) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.carsInShop = carsInShop;
    }
    @Override
    public int getCapacity() {return capacity;}
    @Override
    public void loadCar(T carToLoad) {
        if (carsInShop.size() < capacity) {
            {carsInShop.add(carToLoad);}}
        else{throw new IllegalArgumentException("Shop is full");}
    }
    protected void unLoadCar(T carToUnload){
        carsInShop.remove(carToUnload);
    }
    @Override
    public List<T> getCarsInLoad() {return carsInShop; }

}

