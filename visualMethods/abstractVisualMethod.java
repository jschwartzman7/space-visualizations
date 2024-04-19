import java.util.HashSet;

public abstract class abstractVisualMethod {

    abstractFunction function;
    abstractSpaceVisuals space;
    HashSet<double[]> mappedPoints;


    public abstractVisualMethod(){

    }

    abstract public void run();
    abstract void addFunctionPoints();
}
