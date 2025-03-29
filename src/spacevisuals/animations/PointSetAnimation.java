package spacevisuals.animations;
/*
 * Interface for an animation that traverses a set and acts on each element
 */
public interface PointSetAnimation {
    public abstract void traverseDomain();
    public abstract void handlePoint(double[] input);
}
