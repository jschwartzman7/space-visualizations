import java.util.HashSet;

public abstract class abstractVisualMethod {

    abstractFunction function;
    abstractSpaceVisuals space;
    HashSet<double[]> mappedPoints;
    int resolution;
    

    abstract void run();
    abstract void drawPoints();
}
