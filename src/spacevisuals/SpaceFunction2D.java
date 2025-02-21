package spacevisuals;

import java.util.function.Function;
import spacevisuals.spaces.Euclidean2D;

public class SpaceFunction2D extends SpaceFunction<Euclidean2D>{

    public SpaceFunction2D(){
        super(Euclidean2D.Euclidean2DGet());
    }
    public SpaceFunction2D(Function<double[], double[]> function){
        super(Euclidean2D.Euclidean2DGet(), function);
    }
}
