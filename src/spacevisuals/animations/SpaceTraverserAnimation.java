package spacevisuals.animations;

import java.util.function.Consumer;
import java.util.function.Function;

import spacevisuals.utils.TraverseAnimation;
import spacevisuals.utils.Traverser;
/*
 * Abstract class for an animation that uses a SpaceTraverser and applies a function to elements in a space
 */
public abstract class SpaceTraverserAnimation extends FunctionAnimation implements TraverseAnimation{

    protected Traverser traverser;

    public SpaceTraverserAnimation(Function<double[], double[]> function, Traverser traverser) {
        super(function);
        this.traverser = traverser;
    }

    public SpaceTraverserAnimation(Traverser traverser) {
        super();
        this.traverser = traverser;
    }

    @Override
    public void traverseDomain(Consumer<double[]> handlePoint) {
        traverser.traverseDomain(handlePoint);
    }
}
