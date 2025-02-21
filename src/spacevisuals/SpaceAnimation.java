package spacevisuals;
/*
 * Interface for an animation to be drawn on a space
 */
public interface SpaceAnimation {
    public default void updateAnimation(){};
    public void drawAnimation();
    public void buildAnimation(String[] parameters);
}
