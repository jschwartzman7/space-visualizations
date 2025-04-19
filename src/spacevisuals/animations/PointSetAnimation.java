package spacevisuals.animations;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
/*
 * Interface for an animation that traverses a set and acts on each element
 */
public abstract class PointSetAnimation extends FunctionAnimation{

    protected ArrayList<double[]> points;

    public PointSetAnimation() {
        super();
        this.points = new ArrayList<>();
    }
    public PointSetAnimation(Function<double[], double[]> function) {
        super(function);
        this.points = new ArrayList<>();
    }
    
    @Override
    public void traverseDomain(Consumer<double[]> handlePoint){
        for(double[] point : points){
            handlePoint.accept(point);
        }
    }
}

