package spacevisuals.functionhandling;

import java.util.function.Function;
import spacevisuals.spaces.Euclidean2D;

public class SpaceFunction2D extends SpaceFunction<Euclidean2D>{

    public SpaceFunction2D(){
        super();
        setSpace(Euclidean2D.Get());
    }
    public SpaceFunction2D(Function<double[], double[]> function){
        super(function);
        setSpace(Euclidean2D.Get());
    }
}
