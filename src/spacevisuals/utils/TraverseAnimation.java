package spacevisuals.utils;

public interface TraverseAnimation extends Traverser, ConfigurableAnimation{

    public void handlePoint(double[] input);

    @Override
    public default void drawAnimation(){
        traverseDomain(this::handlePoint);
    }
}
