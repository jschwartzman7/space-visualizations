package spacevisuals.animations;

import java.util.function.Function;

/*
 * Interface for an animation that traverses a set and acts on each element
 */
public abstract class PointSetAnimation extends FunctionAnimation{
    public PointSetAnimation() {
        super();
    }
    public PointSetAnimation(Function<double[], double[]> function) {
        super(function);
    }
    @Override
    public void drawAnimation() {
        traverseDomain();
    }
    public abstract void traverseDomain();
    public abstract void handlePoint(double[] input);
}

