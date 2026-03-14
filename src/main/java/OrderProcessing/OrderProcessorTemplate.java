package OrderProcessing;

public abstract class OrderProcessorTemplate {
    public final void process(){
        validation();
        rahunok();
        reserveStock();
        pay();
        finishing();
    }

    abstract void validation();
    abstract void rahunok();
    abstract void reserveStock();
    abstract void pay();
    abstract void finishing();

}
