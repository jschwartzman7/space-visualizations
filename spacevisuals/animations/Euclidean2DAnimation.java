package spacevisuals.animations;

import spacevisuals.spaces.Euclidean2D;

public class Euclidean2DAnimation extends BasicAnimation<Euclidean2D> {

    public Euclidean2DAnimation(Euclidean2D space, int frameRate){
        super(space, frameRate);
    }

    public static void main(String[] args) {
        new Euclidean2DAnimation(new Euclidean2D(10, 1, true), 25).run();
    }
    
}
