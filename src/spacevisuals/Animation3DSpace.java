package spacevisuals;

import java.util.function.Function;
import spacevisuals.spaces.Euclidean3D;

public class Animation3DSpace implements SpaceAnimation{

    public Euclidean3D space;
    public Function<double[], double[]> function;

    public Animation3DSpace(Euclidean3D space){
        this.space = space;
        this.function = null;
    }
    public Animation3DSpace(Euclidean3D space, Function<double[], double[]> function){
        this.space = space;
        this.function = function;
    }
    
    @Override
    public void drawAnimation() {
        space.drawSpace();
    }
}
