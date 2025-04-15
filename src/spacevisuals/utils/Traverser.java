package spacevisuals.utils;

import java.util.function.Consumer;

public interface Traverser{
    public void traverseDomain(Consumer<double[]> handlePoint);
}
