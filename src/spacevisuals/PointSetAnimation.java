package spacevisuals;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.*;
import spacevisuals.*;

/*
 * Extended interface for an animation that traverses a set and acts on each element
 */
public abstract class PointSetAnimation<T extends AbstractSpace> extends SpaceAnimation<T> {
    public Function<double[], double[]> function;
    public PointSetAnimation(T space, Function<double[], double[]> function){
        super(space);
        this.function = function;
    }
    @Override
    public void drawAnimation(){
        traverseDomain(this::handlePoint);
    };
    public abstract void traverseDomain(Consumer<double[]> handlePoint);
    public abstract void handlePoint(double[] input);
}
