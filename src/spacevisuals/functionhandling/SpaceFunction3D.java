package spacevisuals.functionhandling;

import java.util.function.Function;
import spacevisuals.spaces.Euclidean3D;

public class SpaceFunction3D extends SpaceFunction<Euclidean3D>{

    public SpaceFunction3D(){
        super();
        setSpace(Euclidean3D.Get());
    }
    public SpaceFunction3D(Function<double[], double[]> function){
        super(function);
        setSpace(Euclidean3D.Get());
    }
}
