package spacevisuals.spaces;

import java.util.function.Function;

public class SpaceFunction4D extends SpaceFunction<Euclidean4D>{

    public SpaceFunction4D(){
        super();
        setSpace(Euclidean4D.Get());
    }
    public SpaceFunction4D(Function<double[], double[]> function){
        super(function);
        setSpace(Euclidean4D.Get());
    }
}
