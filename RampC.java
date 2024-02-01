public class RampC<T>{
    private T rampStatus;
    public RampC(T rampStatus) {this.rampStatus = rampStatus;}
    protected T getRampStatus() {return rampStatus;}
    protected void setRampStatus(T variable) {this.rampStatus = variable;}
}
