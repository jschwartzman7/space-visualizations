package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;

import spacevisuals.utils.Constants;
import spacevisuals.utils.Traverser;

public abstract class SpaceTraverser implements Traverser{

    public int resolution;

    public SpaceTraverser(int resolution) {
        this.resolution = resolution;
    }
    public SpaceTraverser() {
        this.resolution = Constants.PIXEL_RESOLUTION_MEDIUM;
    }
    public abstract void traverseDomain(Consumer<double[]> handlePoint);
}
