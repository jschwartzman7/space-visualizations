package spacevisuals;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.spaces.*;
import spacevisuals.*;

/*
 * Extended interface for an animation that traverses a set and acts on each element
 */
public interface PointSetAnimation extends SpaceAnimation {
    @Override
    public default void drawAnimation(){
        traverseDomain(this::handlePoint);
    };
    public abstract void traverseDomain(Consumer<double[]> handlePoint);
    public abstract void handlePoint(double[] input);
}
