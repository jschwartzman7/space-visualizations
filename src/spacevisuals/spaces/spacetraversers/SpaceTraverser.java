package spacevisuals.spaces.spacetraversers;

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
    
}
