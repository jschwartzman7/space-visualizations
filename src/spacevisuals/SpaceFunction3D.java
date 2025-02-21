package spacevisuals;

import java.util.function.Function;
import spacevisuals.spaces.Euclidean3D;

public class SpaceFunction3D extends SpaceFunction<Euclidean3D>{

    public SpaceFunction3D(){
        super(Euclidean3D.Euclidean3DGet());
    }
    public SpaceFunction3D(Function<double[], double[]> function){
        super(Euclidean3D.Euclidean3DGet(), function);
    }
}
