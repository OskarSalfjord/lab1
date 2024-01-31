import java.util.ArrayList;
import java.util.List;

public class AutoShop implements Loadable {
    private final Cartransporter3000 loader = new Cartransporter3000();
    private Car car;
    private int capacity;
    private final List<String> shopRestrictions;
    private List<Car> carsInShop;

    public AutoShop(int capacity, List<String> shopRestrictions, List<Car> carsInShop) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.shopRestrictions = shopRestrictions;
        this.carsInShop = carsInShop;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0) {
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
    }



    @Override
    public void loadCar(Car carToLoad) {
        if (carsInShop.size() < capacity) {
            if (shopRestrictions.contains(carToLoad.getModelName()) || shopRestrictions.isEmpty()) {
                carsInShop.add(carToLoad);}
            else {throw new UnsupportedOperationException("Car type not supported by shop");}}
            else{
            throw new IllegalArgumentException("Shop is full");}
    }

    public void unLoadCar (Car carToUnload){
        carsInShop.remove(carToUnload);
    }
        ;

        public static void main (String[]args){
            List<String> restList1 = new ArrayList<>();
            restList1.add("Saab95");

            Volvo240 volvo1 = new Volvo240();
            Saab95 saab1 = new Saab95();
            Saab95 saab2 = new Saab95();

            List<Saab95> cars = new ArrayList<>();
            cars.add(saab1);

            AutoShop autoshop = new AutoShop(2, restList1, cars);
            autoshop.loadCar(saab2);
            autoshop.unLoadCar(saab1);
            autoshop.loadCar(volvo1);

        }
    }
}

