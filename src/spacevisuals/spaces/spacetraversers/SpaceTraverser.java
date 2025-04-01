package spacevisuals.spaces.spacetraversers;

import java.util.function.Consumer;

public interface SpaceTraverser{
    public void traverseDomain(Consumer<double[]> handlePoint);
}
