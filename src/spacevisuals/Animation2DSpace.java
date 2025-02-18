package spacevisuals;

import java.util.function.Function;

import spacevisuals.spaces.Euclidean2D;

public class Animation2DSpace implements SpaceAnimation{

    public Euclidean2D space;
    public Function<double[], double[]> function;

    public Animation2DSpace(Euclidean2D space){
        this.space = space;
        this.function = null;
    }
    public Animation2DSpace(Euclidean2D space, Function<double[], double[]> function){
        this.space = space;
        this.function = function;
    }
    
    @Override
    public void drawAnimation() {
        space.drawSpace();
    }
    
}
