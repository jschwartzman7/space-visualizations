package spacevisuals.animations;

import java.util.function.Function;

import spacevisuals.spaces.AbstractSpace;
import spacevisuals.spaces.spacetraversers.SpaceTraverser;
/*
 * Abstract class for an animation that uses a SpaceTraverser and applies a function to elements in a space
 */
public abstract class SpaceTraverserAnimation extends FunctionAnimation implements PointSetAnimation {

    SpaceTraverser traverser;

    public SpaceTraverserAnimation(Function<double[], double[]> function, SpaceTraverser traverser) {
        super(function);
        this.traverser = traverser;
    }

    public SpaceTraverserAnimation(SpaceTraverser traverser) {
        super();
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
