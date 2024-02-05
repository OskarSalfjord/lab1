public interface Ramp<T> {
    void raiseRamp();
    void lowerRamp();
    T getRampStatus();
}
