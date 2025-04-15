package spacevisuals.animations;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.utils.TraverseAnimation;

/*
 * Interface for an animation that traverses a set and acts on each element
 */
public abstract class PointSetAnimation extends FunctionAnimation implements TraverseAnimation{

    protected double[][] points;

    public PointSetAnimation() {
        super();
        this.points = new double[][]{};
    }
    public PointSetAnimation(Function<double[], double[]> function) {
        super(function);
        this.points = new double[][]{};
    }
    
    public void traverseDomain(Consumer<double[]> handlePoint){
        for(double[] point : points){
            handlePoint.accept(point);
        }
    }
}

