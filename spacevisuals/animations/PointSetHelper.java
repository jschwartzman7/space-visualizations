package spacevisuals.animations;

import java.util.function.Function;
import spacevisuals.spaces.Euclidean3D;

public interface PointSetHelper{
    void traverseDomain(java.util.function.Consumer<Double[]> handlePoint);
}
