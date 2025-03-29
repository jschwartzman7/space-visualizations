package spacevisuals.animations;

import java.util.function.Function;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
/*
 * Abstract class for an animation that uses a SpaceTraverser and applies a function to elements in a space
 */
public abstract class SpaceTraverserAnimation<T extends AbstractSpace> extends FunctionAnimation<T> implements PointSetAnimation {

    SpaceTraverser<T> traverser;

    public SpaceTraverserAnimation(T space, Function<double[], double[]> function, SpaceTraverser<T> traverser) {
        super(space, function);
        this.traverser = traverser;
    }

    public SpaceTraverserAnimation(T space, SpaceTraverser<T> traverser) {
        super(space);
        this.traverser = traverser;
    }

    @Override
    public void drawAnimation(){
        traverseDomain();
    }

    @Override
    public void traverseDomain() {
        traverser.traverseDomain(this::handlePoint);
    }
}
