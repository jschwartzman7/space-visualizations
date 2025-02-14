package spacevisuals;

import spacevisuals.spaces.AbstractSpace;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Animation<T extends AbstractSpace> {
    default void updateAnimation(){};
    void drawAnimation();
}
