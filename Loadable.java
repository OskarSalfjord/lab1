import java.util.List;

public interface Loadable<T>{
    void loadCar(T carToLoad);
    int getCapacity();
    List<T> getCarsInLoad();
}
